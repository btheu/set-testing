package com.github.btheu.settesting.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.TestInput;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DefaultTestCase implements TestCase {

    protected List<Class<? extends TestInput>> inputClasess = new ArrayList<Class<? extends TestInput>>();

    public DefaultTestCase(TestInput...inputs) {
        for (TestInput testInput : inputs) {
            this.inputClasess.add(testInput.getClass());
        }
    }

}
