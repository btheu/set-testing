package com.github.btheu.settesting.core.impl;

import java.util.Date;

import com.github.btheu.settesting.TestInput;
import com.github.btheu.settesting.core.ReportLine;

public class SimpleReportLine implements ReportLine {

    private boolean success;
    private TestInput[] inputs;
    private Date timestamp = new Date();
    
    public SimpleReportLine(boolean success, TestInput...inputs) {
        this.success = success;
        this.inputs = inputs;
    }

    public boolean success() {
        return success;
    }

    public Date timestamp() {
        return timestamp;
    }

}
