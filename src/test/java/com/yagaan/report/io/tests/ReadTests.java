package com.yagaan.report.io.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
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

public class ReadTests {


	@Test
	public void testReadWithIssues() throws IOException {
		Scan results = new Scan("myLinter","test");
		results.addChecker(new Checker("test.rule1").classification(new Classification().cwe(1)));
		results.addChecker(new Checker("test.rule2").classification(new Classification().cwe(2)));

		Issue issue1 = new Issue("test.rule1", new Fragment("Test1.java", 12)).severity(Severity.BLOCKER);
		Issue issue2 = new Issue("test.rule2", new Fragment("Test1.java", 14)).severity(Severity.BLOCKER);

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ScanIO.write(results, output, issue1,issue2);

		String value = new String(output.toByteArray());
		assertTrue(value.contains("Test1.java"));
	
		List<Issue> issues  = new ArrayList<>();
		Scan read = ScanIO.read(new ByteArrayInputStream(value.getBytes()), issues);
		assertEquals("myLinter",read.getScanner());
		assertEquals("test",read.getApplication());
		assertEquals(2,issues.size());
	}

}
