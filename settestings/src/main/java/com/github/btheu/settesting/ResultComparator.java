package com.github.btheu.settesting;

public interface ResultComparator {

	void compare(User user, UseCase useCase, Result execute);

	void printReport();

}
