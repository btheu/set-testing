package com.github.btheu.settesting.core;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestCase;

public interface GridResult {

    Result get(TestCase testCase);

    void put(Result result, TestCase testCase);

}
