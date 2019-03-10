package com.yagaan.report.documentation;

import java.util.Optional;

import org.apache.commons.lang3.StringEscapeUtils;
import org.markdownj.MarkdownProcessor;

import com.yagaan.report.model.Documentation.Type;

public class MarkdownToHtmlDocumentationProcessor implements IDocumentationProcessor {

	@Override
	public Optional<String> process(String content, Type docType) {
		if (docType != Type.MD) {
			return Optional.empty();
		}
		String html = md2html(content);
		return Optional.of(html);
	}

	private String md2html(String content) {
		return new MarkdownProcessor().markdown(StringEscapeUtils.unescapeXml(content));

	}

}
