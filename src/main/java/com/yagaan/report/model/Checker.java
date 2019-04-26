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

	public Checker(final String name) {
		super();
		this.name = name;
		this.otherDocumentation = new ArrayList<>();
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public String getLanguage() {
		return this.language;
	}

	public Checker language(final String lang) {
		this.language = lang;
		return this;
	}

	public void setCustomSeverity(final String customSeverity) {
		this.customSeverity = customSeverity;
	}

	public String getCustomSeverity() {
		return this.customSeverity;
	}

	public void setSeverity(final Severity severity) {
		this.severity = severity;
	}

	public Checker severity(final Severity severity) {
		this.severity = severity;
		return this;
	}

	public Checker customSeverity(final String severity) {
		this.customSeverity = severity;
		return this;
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public Checker description(final String desc) {
		this.description = desc;
		return this;
	}

	public Checker documentation(final String doc) {
		this.documentation = doc;
		return this;
	}

	/**
	 * Get default HTML documentation of the checker.
	 * 
	 * @return
	 */
	public String getDocumentation() {
		return this.documentation;
	}

	/**
	 * add some other documentation for this checker
	 * 
	 * @param doc
	 * @return
	 */
	public Checker addOtherDocumentation(final Documentation... doc) {
		for (final Documentation documentation : doc) {
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
		return this.otherDocumentation;
	}

	public Checker classification(final Classification classification) {
		this.classification = classification;
		return this;
	}

	/**
	 * Get a description/documentation about the checker.
	 * 
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the name of the checker
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get some classification informations about the issues detected by this
	 * checker.
	 * 
	 * @return
	 */
	public Classification getClassification() {
		return this.classification;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
