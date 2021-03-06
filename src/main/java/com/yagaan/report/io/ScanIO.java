package com.yagaan.report.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.yagaan.report.model.Checker;
import com.yagaan.report.model.Issue;
import com.yagaan.report.model.Scan;

public final class ScanIO {

	private static final String ISSUES = "issues";
	private static final String CHECKERS = "checkers";

	private ScanIO() {

	}

	/**
	 * Serialize a scan as JSON to an output stream.
	 *
	 * @param results
	 * @param output
	 * @throws IOException
	 */
	public static void toJson(final Scan results, final OutputStream output) throws IOException {
		final Gson gson = new Gson();

		IOUtils.write(gson.toJson(results), output, StandardCharsets.UTF_8);
	}

	/**
	 * Load a scan from JSON. In case of very large report file, prefer the read
	 * method that do not require to load the whole file into memory.
	 *
	 * @param results
	 * @param output
	 * @throws IOException
	 */
	public static Scan fromJson(final InputStream input) throws IOException {
		final Gson gson = new Gson();
		return gson.fromJson(IOUtils.toString(input, StandardCharsets.UTF_8), Scan.class);
	}

	/**
	 * Serialize a results objects as JSON to an output stream. Issues are obtained
	 * from a supplier that need to returns null in case of end of the supplied
	 * issues. Use this in case of large number of issues to reduce memory
	 * consumption.
	 *
	 * @param output
	 * @param results
	 * @param issues
	 * @throws IOException
	 */
	public static void write(final Scan results, final Supplier<Issue> issues, final OutputStream output)
			throws IOException {
		final Gson gson = new Gson();
		try (JsonWriter writer = gson.newJsonWriter(new OutputStreamWriter(output))) {
			writer.beginObject();
			writer.name("application").value(results.getApplication());
			writer.name("scanner").value(results.getScanner());
			final Optional<String> language = results.getLanguage();
			if (language.isPresent()) {
				writer.name("language").value(language.get());
			} else {
				writer.name("language").value("");
			}

			writer.name("nbIssues").value(results.getNbIssues());
			writer.name(CHECKERS);
			writer.beginArray();
			for (final Checker checker : results.getCheckers()) {
				writer.jsonValue(gson.toJson(checker));
			}
			writer.endArray();
			writer.name(ISSUES);
			writer.beginArray();
			if (issues != null) {
				Issue issue = issues.get();
				while (issue != null) {
					writer.jsonValue(gson.toJson(issue));
					issue = issues.get();
				}
			}
			writer.endArray();
			writer.endObject();
		}

		output.close();
	}

	/**
	 * Serialize a scan results objects as JSON to an output stream. Issues are
	 * contained into a collection.
	 *
	 * @param output
	 * @param results
	 * @param issues
	 * @throws IOException
	 */
	public static void write(final Scan results, final Collection<Issue> issues, final OutputStream output)
			throws IOException {
		final Iterator<Issue> iterator = issues.iterator();
		write(results, iterator, output);
	}

	public static void write(final Scan results, final Iterator<Issue> iterator, final OutputStream output)
			throws IOException {
		final Supplier<Issue> supplier = () -> {
			if (iterator.hasNext()) {
				return iterator.next();
			}
			return null;
		};
		write(results, supplier, output);
	}

	public static void write(final Scan results, final OutputStream output, final Issue... issues) throws IOException {
		final Iterator<Issue> iterator = Arrays.asList(issues).iterator();
		write(results, iterator, output);
	}

	/**
	 * Read the basic scan informations (name, application and checkers) from a JSON
	 * input stream.
	 *
	 * @return
	 * @throws IOException
	 */

	public static Scan read(final InputStream input) throws IOException {
		return read(input, true);
	}

	/**
	 * Read the basic scan informations (name, application and checkers if asked)
	 * from a JSON input stream.
	 *
	 * @param input        input stream
	 * @param readCheckers true if the checkers are read from Json
	 * @return
	 * @throws IOException
	 */
	public static Scan read(final InputStream input, final boolean readCheckers) throws IOException {
		final Gson gson = new Gson();
		String application = null;
		String scanner = null;
		int nbIssues = -1;
		String language = null;
		List<Checker> checkers = null;
		try (JsonReader reader = gson.newJsonReader(new InputStreamReader(input))) {

			reader.beginObject();
			object_read: while (reader.hasNext()) {
				final String nextName = reader.nextName();
				switch (nextName) {
				case "application":
					application = reader.nextString();
					break;
				case "scanner":
					scanner = reader.nextString();
					break;
				case "nbIssues":
					nbIssues = reader.nextInt();
					break;
				case "language":
					language = reader.nextString();
					break;
				case "checkers":
					if (!readCheckers) {
						if (application == null || scanner == null || nbIssues == -1 || language == null) {
							/* checkers appears before some other values, skip the checkers */
							reader.skipValue();
						} else {
							// we are all set: exit
							break object_read;
						}
					} else {
						reader.beginArray();
						checkers = new ArrayList<>();
						while (reader.hasNext()) {
							final Checker checker = gson.fromJson(reader, Checker.class);
							checkers.add(checker);
						}
						reader.endArray();
					}
					break;
				default:
					if (application == null || scanner == null || nbIssues == -1 || language == null
					|| (readCheckers && checkers == null)) {
						reader.skipValue();
					} else {
						// we are all set: exit
						break object_read;
					}
				}
			}
		}

		final Scan scan = new Scan(scanner, application);
		scan.setNbIssues(nbIssues);
		if (!StringUtils.isEmpty(language)) {
			scan.setLanguage(language);
		}

		if (checkers != null) {
			scan.setCheckers(checkers);
		}

		return scan;
	}

	/**
	 * Read some scan results from a JSON input stream. Issues are added into a
	 * input collection. Prefer to use 'consume' in case of a large input content to
	 * reduce memory consumption.
	 *
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static Scan read(final InputStream input, final Collection<Issue> issues) throws IOException {
		final List<Scan> resultList = new ArrayList<>();

		consume(input, resultList::add, issues::add);
		if (resultList.size() == 1) {
			final Scan results = resultList.get(0);
			return results;
		}
		throw new IllegalStateException("More than one scan in JSON.");
	}

	/**
	 * Consume a JSON export of some scan results.
	 *
	 * @param input
	 * @param mainResultsConsumer consumer of the main results entity (checkers and
	 *                            application name)
	 * @param issuesConsumer      consumer of the issues
	 * @throws IOException
	 */
	public static void consume(final InputStream input, final Consumer<Scan> mainResultsConsumer,
			final Consumer<Issue> issuesConsumer) throws IOException {
		final Gson gson = new Gson();
		String application = null;
		String scanner = null;
		int nbIssues = -1;
		String language = null;
		List<Checker> checkers = null;
		try (JsonReader reader = gson.newJsonReader(new InputStreamReader(input))) {

			reader.beginObject();
			while (reader.hasNext()) {
				final String nextName = reader.nextName();
				switch (nextName) {
				case "application":
					application = reader.nextString();
					break;
				case "scanner":
					scanner = reader.nextString();
					break;
				case "nbIssues":
					nbIssues = reader.nextInt();
					break;
				case "language":
					language = reader.nextString();
					break;
				case "checkers":
					reader.beginArray();
					checkers = new ArrayList<>();
					while (reader.hasNext()) {
						final Checker checker = gson.fromJson(reader, Checker.class);
						checkers.add(checker);
					}
					reader.endArray();
					break;
				case "issues":
					reader.beginArray();
					while (reader.hasNext()) {
						final Issue issue = gson.fromJson(reader, Issue.class);
						issuesConsumer.accept(issue);
					}
					reader.endArray();
					break;
				default:
					throw new IllegalStateException("unexpected property: " + nextName);
				}

				if (application != null && scanner != null && nbIssues != -1 && language != null && checkers != null) {
					final Scan scan = new Scan(scanner, application);
					scan.setNbIssues(nbIssues);
					if (!StringUtils.isEmpty(language)) {
						scan.setLanguage(language);
					}
					scan.setCheckers(checkers);
					mainResultsConsumer.accept(scan);
					application = null;
					scanner = null;
					nbIssues = -1;
					language = null;
					checkers = null;
				}
			}
		}
	}
}
