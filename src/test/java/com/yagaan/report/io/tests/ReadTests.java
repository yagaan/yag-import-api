package com.yagaan.report.io.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yagaan.report.io.ScanIO;
import com.yagaan.report.model.Checker;
import com.yagaan.report.model.Classification;
import com.yagaan.report.model.Fragment;
import com.yagaan.report.model.Issue;
import com.yagaan.report.model.Scan;

public class ReadTests {

	@Test
	public void testReadWithIssues() throws IOException {
		Scan results = new Scan("myLinter", "test");
		results.addChecker(new Checker("test.rule1").classification(new Classification().cwe(1)));
		results.addChecker(new Checker("test.rule2").classification(new Classification().cwe(2)));

		Issue issue1 = new Issue("test.rule1", new Fragment("Test1.java", 12)).message("m1");
		Issue issue2 = new Issue("test.rule2", new Fragment("Test1.java", 14));

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ScanIO.write(results, output, issue1, issue2);

		String value = new String(output.toByteArray());
		assertTrue(value.contains("Test1.java"));

		List<Issue> issues = new ArrayList<>();
		Scan read = ScanIO.read(new ByteArrayInputStream(value.getBytes()), issues);
		assertEquals("myLinter", read.getScanner());
		assertEquals("test", read.getApplication());
		assertEquals(2, issues.size());
		assertEquals("m1", issues.get(0).getMessage());
	}

	@Test
	public void testConsumeWithIssues() throws IOException {
		Scan results = new Scan("myLinter", "test");
		results.addChecker(new Checker("test.rule1").classification(new Classification().cwe(1)));
		results.addChecker(new Checker("test.rule2").classification(new Classification().cwe(2)));

		Issue issue1 = new Issue("test.rule1", new Fragment("Test1.java", 12)).message("m1");
		Issue issue2 = new Issue("test.rule2", new Fragment("Test1.java", 14));

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ScanIO.write(results, output, issue1, issue2);

		String value = new String(output.toByteArray());
		assertTrue(value.contains("Test1.java"));

		List<Issue> issues = new ArrayList<>();
		ScanIO.consume(new ByteArrayInputStream(value.getBytes()), s -> {
		}, issues::add);

		assertEquals(2, issues.size());
		assertEquals("m1", issues.get(0).getMessage());
	}

	@Test
	public void testReadFromExample() throws IOException {
		Scan results = ScanIO.read(new FileInputStream(new File("src/test/resources/example.json")));
		assertEquals("degov", results.getApplication());
	}

	@Test
	public void testReadFromExampleWithCheckers() throws IOException {
		Scan results = ScanIO.read(new FileInputStream(new File("src/test/resources/exampleWithCheckers.json")));
		assertEquals("mespronos", results.getApplication());
		assertEquals(5, results.getCheckers().size());
		assertEquals(Checker.class, results.getCheckers().get(0).getClass());
	}

}
