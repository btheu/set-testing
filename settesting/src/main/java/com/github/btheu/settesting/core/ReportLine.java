package com.github.btheu.settesting.core;

import java.util.Date;

import com.github.btheu.settesting.TestCase;

public interface ReportLine {

    TestCase getTestCase();
    
    Date timestamp();
    
    boolean success();

}
