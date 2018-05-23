package com.yagaan.report.model;

import java.util.ArrayList;
import java.util.List;

public class Scan {
	private int nbIssues;
	private String application;
	private List<Checker> checkers;

	public Scan(String application) {
		super();
		this.application = application;
		this.checkers = new ArrayList<Checker>();
	}

	public void setNbIssues(int nbIssues) {
		this.nbIssues = nbIssues;
	}

	/**
	 * Get the number of issues detected by the scan.
	 * 
	 * @return
	 */
	public int getNbIssues() {
		return nbIssues;
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
