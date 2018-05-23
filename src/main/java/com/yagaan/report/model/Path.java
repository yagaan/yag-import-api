package com.yagaan.report.model;

import java.util.List;

/**
 * A propagation path.
 * 
 * @author antoine
 *
 */
public class Path {
	private List<Fragment> fragments;

	public Path() {

	}

	/**
	 * Add a new fragment into the propagation path.
	 * 
	 * @param fragment
	 * @return
	 */
	public Path add(Fragment fragment) {
		fragments.add(fragment);
		return this;
	}

	/**
	 * Get the fragments sequence of the propagation path.
	 * 
	 * @return
	 */
	public List<Fragment> getFragments() {
		return fragments;
	}
}
