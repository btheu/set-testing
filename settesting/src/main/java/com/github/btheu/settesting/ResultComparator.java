package com.github.btheu.settesting;

public interface ResultComparator {

    @Deprecated
	void compare(User user, UseCase useCase, Result execute);
	
	void compare(Result result, TestInput...inputs);

	void printReport();

}
