package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.ResultValidator;
import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.ValidationException;
import com.github.btheu.settesting.core.GridResult;
import com.github.btheu.settesting.core.GridResultProvider;
import com.github.btheu.settesting.core.Report;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultResultValidator implements ResultValidator {

    @Getter
    private GridResultProvider gridResultProvider;

    protected GridResult gridResult;

    @Setter
    protected Report report;

    public DefaultResultValidator() {

    }

    public void setGridResultProvider(GridResultProvider gridResultProvider) {
        this.gridResult = gridResultProvider.get();
    }

    public boolean compare(Result result, TestCase testCase) {

        Result expected = this.gridResult.get(testCase);
        if (expected == null) {
            log.debug("No expectation found. Use result as default expectation.");
            this.gridResult.put(result, testCase);
            this.report.reportDefault(result, expected, testCase);
            return true;
        } else {
            try {
                this.compare(result, expected, testCase);
                return true;
            } catch (ValidationException e) {
                return false;
            }
        }

    }

    protected void compare(Result result, Result expected, TestCase testCase) throws ValidationException {

        log.debug("compare [{}] with [{}]", result, expected);

        if (result.equals(expected)) {
            this.report.reportSucceed(result, expected, testCase);
        } else {
            this.report.reportFailed(result, expected, testCase);
            throw new ValidationException("Expected " + expected + ", got " + result);
        }
    }

    public Report getReport() {
        return this.report;
    }

    public void validate(Result result, TestCase testCase) throws ValidationException {
        this.compare(result, testCase);
    }

}
