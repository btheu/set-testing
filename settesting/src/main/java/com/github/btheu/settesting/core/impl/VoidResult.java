package com.github.btheu.settesting.core.impl;

import java.util.Map;

import com.github.btheu.settesting.Result;

import lombok.Data;

@Data
public class VoidResult implements Result {

    public Map<String, String> getMetas() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isCorrect(Map<String, String> previousResultMetas) {
        // TODO Auto-generated method stub
        return false;
    }

}
