package com.yagaan.report.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Issue {
	private String name;
	private Fragment location;
	private String message;
	private Status status;
	private String language;

	private Path path;

	private final Map<String, String> metadata;

	public Issue(final String name, final Fragment location) {
		this.name = name;
		this.location = location;
		this.metadata = new HashMap<>();
	}

	public Map<String, String> getMetadata() {
		return this.metadata;
	}

	/**
	 * Get an optional message about the issue explanation
	 *
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Set the explanation message of the issue
	 *
	 * @param message
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * Set the explanation message of the issue
	 *
	 * @param message
	 */
	public Issue message(final String message) {
		this.message = message;
		return this;
	}

	/**
	 * Get the name of the issue checker
	 *
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the location of the vulnerable code
	 *
	 * @return
	 */
	public Fragment getLocation() {
		return this.location;
	}


	public Issue metadata(String key, String value) {
		this.metadata.put(key, value);
		return this;
	}

	public Issue location(final Fragment location) {
		this.location = location;
		return this;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setLocation(final Fragment location) {
		this.location = location;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public Issue status(final Status status) {
		this.status = status;
		return this;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public Issue language(final String language) {
		this.language = language;
		return this;
	}

	public Issue path(final Path propagationPath) {
		this.path = propagationPath;
		return this;
	}

	public void setPath(final Path path) {
		this.path = path;
	}

	/**
	 * Get the review status of the issue.
	 *
	 * @return
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * Get the language of the vulnerable code.
	 *
	 * @return
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Get the optional propagation path that cause the issue.
	 *
	 * @return
	 */
	public Optional<Path> getPath() {
		return Optional.ofNullable(this.path);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
