package com.yagaan.report.model;

import java.util.Optional;

public class Issue {
	private String name;
	private Fragment location;
	private Severity severity;
	private Status status;
	private String language;

	private Path path;

	public Issue(String name, Fragment location) {
		this.name = name;
		this.location = location;
	}

	/**
	 * Get the name of the issue checker
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the location of the vulnerable code
	 * 
	 * @return
	 */
	public Fragment getLocation() {
		return location;
	}

	public Issue location(Fragment location) {
		this.location = location;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(Fragment location) {
		this.location = location;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Issue severity(Severity severity) {
		this.severity = severity;
		return this;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Issue status(Status status) {
		this.status = status;
		return this;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Issue language(String language) {
		this.language = language;
		return this;
	}

	public Issue path(Path propagationPath) {
		this.path = propagationPath;
		return this;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * Get the severity of the issue.
	 * 
	 * @return
	 */
	public Severity getSeverity() {
		return severity;
	}

	/**
	 * Get the review status of the issue.
	 * 
	 * @return
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Get the language of the vulnerable code.
	 * 
	 * @return
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Get the optional propagation path that cause the issue.
	 * 
	 * @return
	 */
	public Optional<Path> getPath() {
		return Optional.ofNullable(path);
	}

	@Override
	public String toString() {
		return name;
	}

}
