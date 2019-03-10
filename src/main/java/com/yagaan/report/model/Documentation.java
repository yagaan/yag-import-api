package com.yagaan.report.model;

/**
 * A documentation can be in different format (markdown, HTML ,etc.) and written for
 * different languages. Documentation class hold all that informations.
 * 
 * @author antoine
 *
 */
public class Documentation {

	/** enumeration of type of documentation : Markdown, HTML */
	public enum Type {
		MD, HTML
	}

	/** content of the documentation */
	private String content;
	private Type type;
	private String language;

	/**
	 * html type is the default type of documentation
	 * 
	 * @param content
	 */
	public Documentation(String content) {
		this(content, Type.HTML);
	}

	public Documentation(String content, Type type) {
		this.content = content;
		this.type = type;
	}

	/**
	 * language of the documentation
	 * 
	 * @return
	 */
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Documentation language(String language) {
		this.language = language;
		return this;
	}

	/**
	 * type of the documentation
	 * 
	 * @return
	 */
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Documentation type(Type type) {
		this.type = type;
		return this;
	}

	/**
	 * content of the documentation
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Documentation content(String content) {
		this.content = content;
		return this;
	}

}
