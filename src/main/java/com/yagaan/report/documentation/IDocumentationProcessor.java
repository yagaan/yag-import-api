package com.yagaan.report.documentation;

import java.util.Optional;

import com.yagaan.report.model.Documentation.Type;

public interface IDocumentationProcessor {

	/**
	 * Process the documentation if the doc type is supported
	 * @param content
	 * @param docType
	 * @return
	 */
	Optional<String> process(String content, Type docType);
}
