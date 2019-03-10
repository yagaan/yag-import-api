package com.yagaan.report.documentation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.yagaan.report.model.Documentation.Type;

/**
 * Documentation compose build a single json file containing all translations of
 * documentations of some checkers. Expected documentation directory structure
 * is: <br>
 * 
 * <pre>
 * scanner/
 *   en/
 *     descriptor.json
 *     rule1.md
 *     rule2.md
 *     ...
 *   fr/
 *     descriptor.json
 *     rule1.md
 * </pre>
 * 
 * Documentation can be post processed to be transformed into expected output
 * format if some {@link IDocumentationProcessor} are added to the composer.
 * 
 * @author antoine
 *
 */
public class DocumentationComposer {
	private File mainDirectory;
	private List<IDocumentationProcessor> processors;

	public DocumentationComposer(File mainDirectory) {
		super();
		this.mainDirectory = mainDirectory;
		this.processors = new  ArrayList<>();
	}

	public DocumentationComposer processedBy(IDocumentationProcessor processor) {
		this.processors.add(processor);
		return this;
	}
	
	private File getLanguageDirectory(String lang) {
		return new File(mainDirectory, lang);
	}

	private File getDescriptorFile(String lang) {
		return new File(getLanguageDirectory(lang), "descriptor.json");

	}
	

	/**
	 * Compose all checkers documentation into a single json file per language.
	 * Output directory structure will be:
	 * 
	 * <pre>
	 *   en/
	 *     scanner.json
	 *   fr/
	 *     scanner.json
	 * </pre>
	 * 
	 * This kind of structure can be used by any i18n tool to manage translation of
	 * checkers.
	 * 
	 * @param output
	 * @throws IOException
	 */
	public void compose(File output) throws IOException {
		File[] languages = mainDirectory.listFiles();
		String scanner = mainDirectory.getName();
		for (File file : languages) {
			if (file.isDirectory() && !file.getName().startsWith(".")) {
				String lang = FilenameUtils.getBaseName(file.getName());
				ScannerDocumentation doc = compose(lang);
				File outputFile = new File(new File(output, lang), scanner + ".json");
				FileUtils.write(outputFile, doc.toJson(), StandardCharsets.UTF_8);
			}
		}
	}

	/**
	 * Build a composed scanner documentation based on the content of language
	 * directory.
	 * 
	 * @param title
	 * @param version
	 * @param lang
	 * @return
	 * @throws IOException
	 */
	public ScannerDocumentation compose(String lang) throws IOException {
		File descriptorFile = getDescriptorFile(lang);
		if (!descriptorFile.exists()) {
			throw new IllegalStateException("No documentation descriptor file for language '" + lang + "' expecting '"
					+ lang + "/descriptor.json' file.");
		}
		DocumentationDescriptor descriptor = new Gson().fromJson(
				FileUtils.readFileToString(descriptorFile, StandardCharsets.UTF_8), DocumentationDescriptor.class);
		ScannerDocumentation doc = new ScannerDocumentation(descriptor.getTitle(), descriptor.getVersion(), lang);
		File languageDirectory = getLanguageDirectory(lang);
		if (languageDirectory.exists()) {
			File[] children = languageDirectory.listFiles();
			for (File child : children) {
				if (!child.getName().equals("descriptor.json")) {
					String baseName = FilenameUtils.getBaseName(child.getName());
					String content = FileUtils.readFileToString(child, StandardCharsets.UTF_8);
					doc.set(baseName, process(content, descriptor.getType()));
				}
			}
		}
		return doc;
	}

	private String process(String content, Type docType) {
		for (IDocumentationProcessor iDocumentationProcessor : processors) {
			Optional<String> processed = iDocumentationProcessor.process(content, docType);
			if (processed.isPresent()) {
				return processed.get();
			}
		}
		return content;
	}

}
