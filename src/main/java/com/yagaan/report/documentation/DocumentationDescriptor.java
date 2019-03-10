package com.yagaan.report.documentation;

import com.yagaan.report.model.Documentation;

public class DocumentationDescriptor {
	private String title;
	private Documentation.Type type;

	private String language;
	private String version;

	public DocumentationDescriptor() {

	}

	public Documentation.Type getType() {
		return type;
	}

	public void setType(Documentation.Type type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
