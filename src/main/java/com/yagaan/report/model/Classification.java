package com.yagaan.report.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide some classification information about an issue.
 * 
 * @author antoine
 *
 */
public class Classification {
	private List<String> tags;

	public Classification() {
		this.tags = new ArrayList<String>();
	}

	public Classification owasp2013(int number) {
		tags.add("owasp-A" + number);
		return this;
	}

	public Classification cwe(int number) {
		tags.add("cwe-" + number);
		return this;
	}

	public Classification pci_dss(int number) {
		tags.add("pcidss-" + number);
		return this;
	}

	public Classification owasp2017(int number) {
		tags.add("owasp-2017-A" + number);
		return this;
	}

	public List<String> getTags() {
		return tags;
	}

	@Override
	public String toString() {
		return tags.toString();
	}

}
