package com.github.btheu.settesting.core;

import java.util.List;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestInput;

public interface ResultReport {

    void reportDefault(Result result, Result expected, TestInput...inputs);

    void reportFailed(Result result, Result expected, TestInput...inputs);

    void reportSucceed(Result result, Result expected, TestInput...inputs);

    List<ReportLine> getAllLines();

}
