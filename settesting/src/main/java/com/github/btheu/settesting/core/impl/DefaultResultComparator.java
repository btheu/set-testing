package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.ResultComparator;
import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.core.GridResult;
import com.github.btheu.settesting.core.GridResultProvider;
import com.github.btheu.settesting.core.Report;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultResultComparator implements ResultComparator {

    @Getter
    private GridResultProvider gridResultProvider;

    protected GridResult gridResult;

    @Setter
    protected Report report;
    
    
    public DefaultResultComparator() {

    }

    public void setGridResultProvider(GridResultProvider gridResultProvider) {
        gridResult = gridResultProvider.get();
    }
    
    public void compare(Result result, TestCase testCase) {
        
        Result expected = gridResult.get(testCase);
        if(expected == null){
            gridResult.put(result, testCase);
            report.reportDefault(result, expected, testCase);
        }else{
            compare(result, expected, testCase);
        }
        
    }

    protected void compare(Result result, Result expected, TestCase testCase) {

        log.debug("compare [{}] with [{}]",result, expected);

        if(result.equals(expected)){
            report.reportSucceed(result, expected, testCase);
        }else{
            report.reportFailed(result, expected, testCase);
        }
        
    }

    public Report getReport() {
        return report;
    }




}
