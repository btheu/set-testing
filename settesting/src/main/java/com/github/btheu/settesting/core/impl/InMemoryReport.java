package com.github.btheu.settesting.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.core.ReportLine;
import com.github.btheu.settesting.core.Report;

public class InMemoryReport implements Report {


    List<ReportLine> reportLines = new ArrayList<ReportLine>();

    public List<ReportLine> getAllLines() {
        return new ArrayList<ReportLine>(reportLines);
    }

    public void reportDefault(Result result, Result expected,
            TestCase testCase) {
        reportLines.add( new SimpleReportLine(false, testCase));
        
    }

    public void reportFailed(Result result, Result expected,
            TestCase testCase) {
        reportLines.add( new SimpleReportLine(false, testCase));
    }

    public void reportSucceed(Result result, Result expected,
            TestCase testCase) {
        reportLines.add( new SimpleReportLine(true, testCase));
    }

}
