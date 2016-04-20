package com.github.btheu.settesting.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.btheu.settesting.Result;
import com.github.btheu.settesting.TestInput;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultGridResult implements GridResult {

    Map<Key, Result> results = new HashMap<Key, Result>();

    public Result get(TestInput... inputs) {
        return results.get(new Key(inputs));

    }

    public void put(Result result, TestInput... inputs) {
        log.debug("put default result [{}]", result);

        results.put(new Key(inputs), result);

    }

    @Data
    @EqualsAndHashCode
    public static class Key {

        private TestInput[] inputs;

        public Key(TestInput[] inputs) {
            this.inputs = inputs;
        }

    }

}
