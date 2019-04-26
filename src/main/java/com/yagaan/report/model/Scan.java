package com.yagaan.report.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scan {
	private String scanner;
	private int nbIssues;
	private String application;
	private String language;
	private List<Checker> checkers;
	private List<Issue> issues;

	public Scan(final String scanner, final String application) {
		super();
		this.scanner = scanner;
		this.application = application;
		this.checkers = new ArrayList<>();
		this.issues = new ArrayList<>();
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	/**
	 * Get the default language of this scan.
	 * 
	 * @return
	 */
	public Optional<String> getLanguage() {
		return Optional.ofNullable(this.language);
	}

	/**
	 * Set the number of detected issues.
	 * 
	 * @param nbIssues
	 */
	public void setNbIssues(final int nbIssues) {
		this.nbIssues = nbIssues;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(final List<Issue> issues) {
		this.issues = issues;
	}

	/**
	 * Set the name of scanner product.
	 * 
	 * @param scanner
	 */
	public void setScanner(final String scanner) {
		this.scanner = scanner;
	}

	/**
	 * Get the name of the scanner product that detect the issues.
	 * 
	 * @return
	 */
	public String getScanner() {
		return this.scanner;
	}

	/**
	 * Get the number of issues detected by the scan.
	 * 
	 * @return
	 */
	public int getNbIssues() {
		return this.nbIssues;
	}

	public Scan issues(final int nbIssues) {
		this.nbIssues = nbIssues;
		return this;
	}

	/**
	 * Add a checker description to this scan. This description will be used to
	 * insert documentation and classification informations into the YAG Suite.
	 * 
	 * @param checker
	 */
	public void addChecker(final Checker checker) {
		this.checkers.add(checker);
	}

	public void setCheckers(final List<Checker> checkers) {
		this.checkers = checkers;
	}

	/**
	 * Get the scanned application name.
	 * 
	 * @return
	 */
	public String getApplication() {
		return this.application;
	}

	/**
	 * Get all known issues checkers descriptions. This descriptions will be used to
	 * insert documentation and classification informations into the YAG Suite.
	 * 
	 * @return
	 */
	public List<Checker> getCheckers() {
		return this.checkers;
	}

}
