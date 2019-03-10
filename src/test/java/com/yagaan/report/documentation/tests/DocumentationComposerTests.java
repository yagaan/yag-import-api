package com.yagaan.report.documentation.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.junit.Test;

import com.yagaan.report.documentation.DocumentationComposer;
import com.yagaan.report.documentation.I18nDocumentation;
import com.yagaan.report.documentation.MarkdownToHtmlDocumentationProcessor;

public class DocumentationComposerTests {

	
	@Test
	public void shouldProduceComposedDoc() throws IOException {
		DocumentationComposer composer = new DocumentationComposer(new File("src/test/resources/doc/myScanner"));
		composer.processedBy(new MarkdownToHtmlDocumentationProcessor());
		File i18n = new File("target/i18n");
		composer.compose(i18n);
		
		I18nDocumentation doc = new I18nDocumentation("en", i18n);
		Optional<String> html = doc.get("myScanner","fr", "rule1");
		assertTrue(html.isPresent());
	}
}
