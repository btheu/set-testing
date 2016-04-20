package com.github.btheu.settesting;

public interface User {

	void create();

	void validate();

	void authenticate();

	/**
	 * Destroy all authenticated status.
	 * 
	 */
	void invalidate();

	void remove();

}
