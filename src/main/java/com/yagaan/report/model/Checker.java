package com.yagaan.report.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A checker is the rule used by the scanner to detect some issues into the
 * scanned application.
 * 
 * @author antoine
 *
 */
public class Checker {
	private String name;
	private String language;
	private String description;
	/** default documentation in html */
	private String documentation;
	private List<Documentation> otherDocumentation;
	private Severity severity;
	private String customSeverity;
	private Classification classification;

	public Checker(String name) {
		super();
		this.name = name;
		this.otherDocumentation = new ArrayList<Documentation>();
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public Checker language(String lang) {
		this.language = lang;
		return this;
	}

	public void setCustomSeverity(String customSeverity) {
		this.customSeverity = customSeverity;
	}

	public String getCustomSeverity() {
		return customSeverity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Checker severity(Severity severity) {
		this.severity = severity;
		return this;
	}

	public Checker customSeverity(String severity) {
		this.customSeverity = severity;
		return this;
	}

	public Severity getSeverity() {
		return severity;
	}

	public Checker description(String desc) {
		this.description = desc;
		return this;
	}

	public Checker documentation(String doc) {
		this.documentation = doc;
		return this;
	}

	/**
	 * Get default HTML documentation of the checker.
	 * 
	 * @return
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * add some other documentation for this checker
	 * 
	 * @param doc
	 * @return
	 */
	public Checker addOtherDocumentation(Documentation... doc) {
		for (Documentation documentation : doc) {
			this.otherDocumentation.add(documentation);
		}
		return this;
	}

	/**
	 * Get alternative documentations of the checker.
	 * 
	 * @return
	 */
	public List<Documentation> getOtherDocumentation() {
		return otherDocumentation;
	}

	public Checker classification(Classification classification) {
		this.classification = classification;
		return this;
	}

	/**
	 * Get a description/documentation about the checker.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the name of the checker
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get some classification informations about the issues detected by this
	 * checker.
	 * 
	 * @return
	 */
	public Classification getClassification() {
		return classification;
	}

	@Override
	public String toString() {
		return name;
	}
}
