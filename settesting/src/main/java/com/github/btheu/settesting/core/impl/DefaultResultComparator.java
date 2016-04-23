package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.ResultComparator;
import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.core.GridResult;
import com.github.btheu.settesting.core.GridResultProvider;
import com.github.btheu.settesting.core.ResultReport;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultResultComparator implements ResultComparator {

    @Getter
    private GridResultProvider gridResultProvider;

    protected GridResult gridResult;

    @Setter
    protected ResultReport resultReport;
    
    
    public DefaultResultComparator() {

    }

    public void setGridResultProvider(GridResultProvider gridResultProvider) {
        gridResult = gridResultProvider.get();
    }
    
    public void compare(Result result, TestCase testCase) {
        
        Result expected = gridResult.get(testCase);
        if(expected == null){
            gridResult.put(result, testCase);
            resultReport.reportDefault(result, expected, testCase);
        }else{
            compare(result, expected, testCase);
        }
        
    }

    protected void compare(Result result, Result expected, TestCase testCase) {

        log.debug("compare [{}] with [{}]",result, expected);

        if(result.equals(expected)){
            resultReport.reportSucceed(result, expected, testCase);
        }else{
            resultReport.reportFailed(result, expected, testCase);
        }
        
    }

    public ResultReport getReport() {
        return resultReport;
    }




}
