package com.yagaan.report.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Source code location information.
 *
 * @author antoine
 *
 */
public class Fragment {
	private String file;
	private int line;
	private int eline = -1;
	private int column = -1;
	private int ecolumn = -1;
	private int length = -1;

	private final Map<String, String> metadata;

	/**
	 *
	 * @param file file path
	 * @param line line number, 1 based.
	 */
	public Fragment(final String file, final int line) {
		super();
		this.file = file;
		this.line = line;
		this.metadata = new HashMap<>();
	}

	public Map<String, String> getMetadata() {
		return this.metadata;
	}
	public Fragment metadata(String key, String value) {
		this.metadata.put(key, value);
		return this;
	}

	/**
	 *
	 * @param column column number, 0-based
	 * @return the current fragment
	 */
	public Fragment column(final int column) {
		this.column = column;
		return this;
	}

	public Fragment length(final int length) {
		this.length = length;
		return this;
	}

	/**
	 *
	 * @param col a column number, 0-based
	 * @return then current fragment
	 */
	public Fragment endColumn(final int col) {
		this.ecolumn = col;
		return this;
	}

	/**
	 *
	 * @param line a line number, 1-based
	 * @return then current fragment
	 */
	public Fragment endLine(final int line) {
		this.eline = line;
		return this;
	}

	/**
	 * @return the last column of the selection, 0-based, or -1 if undefined
	 */
	public int getEndColumn() {
		return this.ecolumn;
	}

	/**
	 * @return the last line of the selection, 1-based, or -1 if undefined
	 */
	public int getEndLine() {
		return this.eline;
	}

	public boolean hasColumn() {
		return this.column != -1;
	}

	public boolean hasLength() {
		return this.length != -1;
	}

	/**
	 * Get the source code file relative path of the fragment.
	 *
	 * @return
	 */
	public String getFile() {
		return this.file;
	}

	/**
	 * Set the source code file relative path of the fragment.
	 *
	 * @param file
	 */
	public void setFile(final String file) {
		this.file = file;
	}

	/**
	 * Get the start line of the code fragment, 1-based.
	 *
	 * @return
	 */
	public int getLine() {
		return this.line;
	}

	/**
	 * Set the start line of the code fragment, 1-based.
	 *
	 * @param line
	 */
	public void setLine(final int line) {
		this.line = line;
	}

	/**
	 * Get the start column of the code fragment, 0-based.
	 *
	 * @return
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * Set the start column of the code fragment, 0-based.
	 *
	 * @param column
	 */
	public void setColumn(final int column) {
		this.column = column;
	}

	/**
	 * Get the length of the code fragment, or -1 if undefined.
	 *
	 * @return
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Set the length of the code fragment.
	 *
	 * @param length
	 */
	public void setLength(final int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return this.file + " at l." + this.line;
	}
}
