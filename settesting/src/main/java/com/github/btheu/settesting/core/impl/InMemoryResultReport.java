package com.github.btheu.settesting.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestInput;
import com.github.btheu.settesting.core.ReportLine;
import com.github.btheu.settesting.core.ResultReport;

public class InMemoryResultReport implements ResultReport {


    List<ReportLine> reportLines = new ArrayList<ReportLine>();

    public List<ReportLine> getAllLines() {
        return new ArrayList<ReportLine>(reportLines);
    }

    public void reportDefault(Result result, Result expected,
            TestInput... inputs) {
        reportLines.add( new SimpleReportLine(false, inputs));
    }

    public void reportFailed(Result result, Result expected,
            TestInput... inputs) {
        reportLines.add( new SimpleReportLine(false, inputs));
    }

    public void reportSucceed(Result result, Result expected,
            TestInput... inputs) {
        reportLines.add( new SimpleReportLine(true, inputs));
    }

}
