package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.ResultComparator;
import com.github.btheu.settesting.TestInput;
import com.github.btheu.settesting.UseCase;
import com.github.btheu.settesting.User;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultResultComparator implements ResultComparator {

    @Getter
    private GridResultProvider gridResultProvider;

    protected GridResult gridResult;

    public DefaultResultComparator() {

    }

    public void setGridResultProvider(GridResultProvider gridResultProvider) {
        gridResult = gridResultProvider.get();
    }
    
    public void compare(Result result, TestInput...inputs) {

        Result expected = gridResult.get(inputs);
        if(expected == null){
            gridResult.put(result, inputs);
        }else{
            compare(result,expected);
        }

    }

    protected void compare(Result result, Result expected) {

        log.info("compare [{}] with [{}]",result, expected);

    }

    public void printReport() {
        // TODO Auto-generated method stub

    }


    @Deprecated
    public void compare(User user, UseCase useCase, Result execute) {
        // TODO Auto-generated method stub

    }


}
