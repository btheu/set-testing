package com.github.btheu.settesting.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.core.GridResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultGridResult implements GridResult {

    protected Map<TestCase, Result> results = new HashMap<TestCase, Result>();

    public Result get(TestCase testCase) {
        return results.get(testCase);
    }

    public void put(Result result, TestCase testCase) {
        log.debug("put default result [{}]", result);

        results.put(testCase, result);
    }


}
