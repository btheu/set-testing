package com.github.btheu.settesting;

public interface ResultValidator {

	/**
	 * 
	 * @param result
	 * @param testCase
	 * 
	 * @return false if test case failed, true otherwise
	 */
	@Deprecated
    boolean compare(Result result, TestCase testCase);
    
    void validate(Result result, TestCase testCase) throws ValidationException;
    
}
