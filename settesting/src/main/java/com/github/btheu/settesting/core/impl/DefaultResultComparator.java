package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.ResultComparator;
import com.github.btheu.settesting.TestInput;
import com.github.btheu.settesting.UseCase;
import com.github.btheu.settesting.User;
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
    
    public void compare(Result result, TestInput...inputs) {

        Result expected = gridResult.get(inputs);
        if(expected == null){
            gridResult.put(result, inputs);
            resultReport.reportDefault(result, expected, inputs);
        }else{
            compare(result, expected, inputs);
        }

    }

    protected void compare(Result result, Result expected, TestInput...inputs) {

        log.info("compare [{}] with [{}]",result, expected);

        if(result == expected){
            resultReport.reportSucceed(result, expected, inputs);
        }else{
            resultReport.reportFailed(result, expected, inputs);
        }
        
    }

    @Deprecated
    public void printReport() {
        // TODO Auto-generated method stub

    }


    @Deprecated
    public void compare(User user, UseCase useCase, Result execute) {
        // TODO Auto-generated method stub

    }

    public ResultReport getReport() {
        return resultReport;
    }


}
