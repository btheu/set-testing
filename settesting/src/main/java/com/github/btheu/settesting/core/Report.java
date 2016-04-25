package com.github.btheu.settesting.core;

import java.util.List;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestCase;

public interface Report {

    void reportDefault(Result result, Result expected, TestCase testCase);
    
    void reportFailed(Result result, Result expected, TestCase testCase);
    
    void reportSucceed(Result result, Result expected, TestCase testCase);

    List<ReportLine> getAllLines();

    

}
