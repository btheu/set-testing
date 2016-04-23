package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.TestCase;
import com.github.btheu.settesting.TestInput;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DefaultTestCase implements TestCase {

    private TestInput[] inputs;

    public DefaultTestCase(TestInput...inputs) {
        this.inputs = inputs;
    }

}
