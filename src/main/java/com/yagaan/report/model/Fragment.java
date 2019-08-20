package com.yagaan.report.model;

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

	/**
	 * 
	 * @param file file path
	 * @param line line number, 1 based.
	 */
	public Fragment(String file, int line) {
		super();
		this.file = file;
		this.line = line;
	}

	/**
	 * 
	 * @param column column number, 0-based
	 * @return the current fragment
	 */
	public Fragment column(int column) {
		this.column = column;
		return this;
	}

	public Fragment length(int length) {
		this.length = length;
		return this;
	}

	/**
	 * 
	 * @param col a column number, 0-based
	 * @return then current fragment
	 */
	public Fragment endColumn(int col) {
		this.ecolumn = col;
		return this;
	}

	/**
	 * 
	 * @param line a line number, 1-based
	 * @return then current fragment
	 */
	public Fragment endLine(int line) {
		this.eline = line;
		return this;
	}

	/**
	 * @return the last column of the selection, 0-based, or -1 if undefined
	 */
	public int getEndColumn() {
		return ecolumn;
	}

	/**
	 * @return the last line of the selection, 1-based, or -1 if undefined
	 */
	public int getEndLine() {
		return eline;
	}

	public boolean hasColumn() {
		return column != -1;
	}

	public boolean hasLength() {
		return length != -1;
	}

	/**
	 * Get the source code file relative path of the fragment.
	 * 
	 * @return
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Set the source code file relative path of the fragment.
	 * 
	 * @param file
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * Get the start line of the code fragment, 1-based.
	 * 
	 * @return
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Set the start line of the code fragment, 1-based.
	 * 
	 * @param line
	 */
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * Get the start column of the code fragment, 0-based.
	 * 
	 * @return
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Set the start column of the code fragment, 0-based.
	 * 
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Get the length of the code fragment, or -1 if undefined.
	 * 
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Set the length of the code fragment.
	 * 
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return file + " at l." + line;
	}
}
