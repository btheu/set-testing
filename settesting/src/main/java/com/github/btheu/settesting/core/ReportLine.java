package com.github.btheu.settesting.core;

import java.util.Date;

public interface ReportLine {

    Date timestamp();
    
    boolean success();

}
