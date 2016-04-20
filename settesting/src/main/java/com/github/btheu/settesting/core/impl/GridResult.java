package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestInput;

public interface GridResult {

    Result get(TestInput...inputs);

    void put(Result result, TestInput...inputs);

}
