package com.github.btheu.settesting.core.impl;

import java.util.Date;

import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.core.ReportLine;

public class SimpleReportLine implements ReportLine {

    private boolean success;
    private Date timestamp = new Date();
    private TestCase testCase;
    
    public SimpleReportLine(boolean success, TestCase testCase) {
        this.success = success;
        this.testCase = testCase;
    }

    public boolean success() {
        return success;
    }

    public Date timestamp() {
        return timestamp;
    }

    public TestCase getTestCase() {
        return testCase;
    }

}
