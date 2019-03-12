package com.yagaan.report.model;

import java.util.ArrayList;
import java.util.List;

public class Scan {
	private String scanner;
	private int nbIssues;
	private String application;
	private List<Checker> checkers;
	private List<Issue> issues;

	public Scan(String scanner,String application) {
		super();
		this.scanner = scanner;
		this.application = application;
		this.checkers = new ArrayList<Checker>();
		this.issues = new ArrayList<>();
	}

	/**
	 * Set the number of detected issues.
	 * 
	 * @param nbIssues
	 */
	public void setNbIssues(int nbIssues) {
		this.nbIssues = nbIssues;
	}

	
	public List<Issue> getIssues() {
		return issues;
	}
	
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
	/**
	 * Set the name of scanner product.
	 * 
	 * @param scanner
	 */
	public void setScanner(String scanner) {
		this.scanner = scanner;
	}

	/**
	 * Get the name of the scanner product that detect the issues.
	 * @return
	 */
	public String getScanner() {
		return scanner;
	}

	/**
	 * Get the number of issues detected by the scan.
	 * 
	 * @return
	 */
	public int getNbIssues() {
		return nbIssues;
	}
	
	public Scan issues(int nbIssues) {
		this.nbIssues = nbIssues;
		return this;
	}

	/**
	 * Add a checker description to this scan. This description will be used to
	 * insert documentation and classification informations into the YAG Suite.
	 * 
	 * @param checker
	 */
	public void addChecker(Checker checker) {
		checkers.add(checker);
	}

	public void setCheckers(List<Checker> checkers) {
		this.checkers = checkers;
	}

	/**
	 * Get the scanned application name.
	 * 
	 * @return
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * Get all known issues checkers descriptions. This descriptions will be used to
	 * insert documentation and classification informations into the YAG Suite.
	 * 
	 * @return
	 */
	public List<Checker> getCheckers() {
		return checkers;
	}

}
