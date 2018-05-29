package com.yagaan.report.io.tests;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

import com.yagaan.report.io.ScanIO;
import com.yagaan.report.model.Checker;
import com.yagaan.report.model.Classification;
import com.yagaan.report.model.Fragment;
import com.yagaan.report.model.Issue;
import com.yagaan.report.model.Scan;
import com.yagaan.report.model.Severity;

public class WriteTests {

	@Test
	public void testWriteWithoutIssues() throws IOException {
		Scan results = new Scan("myLinter","test");
		results.addChecker(new Checker("test.rule1").classification(new Classification().cwe(1)));
		results.addChecker(new Checker("test.rule2").classification(new Classification().cwe(2)));

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ScanIO.write(results, new ArrayList<>(), output);
		String value = new String(output.toByteArray());
		assertTrue(value.contains("test.rule1"));
	}

	@Test
	public void testWriteWithIssues() throws IOException {
		Scan results = new Scan("myLinter","test");
		results.addChecker(new Checker("test.rule1").classification(new Classification().cwe(1)));
		results.addChecker(new Checker("test.rule2").classification(new Classification().cwe(2)));

		Issue issue = new Issue("test.rule1", new Fragment("Test1.java", 12)).severity(Severity.BLOCKER);

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ScanIO.write(results, output, issue);

		String value = new String(output.toByteArray());
		assertTrue(value.contains("Test1.java"));
	}

	@Test
	public void testWriteWithIssuesAndSupplier() throws IOException {
		Scan results = new Scan("myLinter","test");
		results.addChecker(new Checker("test.rule1").classification(new Classification().cwe(1)));
		results.addChecker(new Checker("test.rule2").classification(new Classification().cwe(2)));

		List<Issue> issues = new ArrayList<Issue>();
		issues.add(new Issue("test.rule1", new Fragment("Test1.java", 12)).severity(Severity.BLOCKER));
		Iterator<Issue> iterator = issues.iterator();
		Supplier<Issue> supplier = new Supplier<Issue>() {

			@Override
			public Issue get() {
				if (iterator.hasNext()) {
					return iterator.next();
				}
				return null;
			}
		};

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ScanIO.write(results, supplier, output);

		String value = new String(output.toByteArray());
		assertTrue(value.contains("Test1.java"));
	}
}
