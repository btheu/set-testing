package com.github.btheu.settesting.core.impl;

public class InMemoryGridResultProvider implements GridResultProvider {

    GridResult gridResult = new DefaultGridResult();
    
    public GridResult get() {
        return gridResult;
    }

}
