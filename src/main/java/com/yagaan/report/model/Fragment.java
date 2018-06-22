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

	public Fragment(String file, int line) {
		super();
		this.file = file;
		this.line = line;
	}

	public Fragment column(int column) {
		this.column = column;
		return this;
	}

	public Fragment length(int length) {
		this.length = length;
		return this;
	}

	public Fragment endColumn(int col) {
		this.ecolumn = col;
		return this;
	}

	public Fragment endLine(int line) {
		this.eline = line;
		return this;
	}

	public int getEndColumn() {
		return ecolumn;
	}

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
	 * Get the start line of the code fragment.
	 * 
	 * @return
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Set the start line of the code fragment.
	 * 
	 * @param line
	 */
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * Get the start column of the code fragment.
	 * 
	 * @return
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Set the start column of the code fragment.
	 * 
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Get the length of the code fragment.
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
