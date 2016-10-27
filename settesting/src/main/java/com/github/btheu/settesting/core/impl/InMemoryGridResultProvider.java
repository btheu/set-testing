package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.core.GridResult;
import com.github.btheu.settesting.core.GridResultProvider;

public class InMemoryGridResultProvider implements GridResultProvider {

    GridResult gridResult = new DefaultGridResult();
    
    public GridResult get() {
        return gridResult;
    }

}
