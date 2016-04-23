package com.github.btheu.settesting;

import org.junit.Test;

import com.github.btheu.settesting.core.impl.PrimitiveValueResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultComparatorTest extends SetTestingCase {

    @Test 
    public void test2() {
        this.usecases.add(SimpleUseCase1.class);
        this.bos.add(SimpleObject.class);
        this.bos.add(SimpleObject.class);
        this.bos.add(SimpleObject.class);
    }
 
    static class SimpleUseCase1 implements UseCase {

        public Result execute() {
            return new PrimitiveValueResult(1);
        }
        
    }
    
    static class SimpleUseCase2 implements UseCase {
        
        public Result execute() {
            return new PrimitiveValueResult(1);
        }
        
    }
    
    static class SimpleObject implements BusinessObject {

        public void create() {
            // TODO Auto-generated method stub
            
        }

        public void remove() {
            // TODO Auto-generated method stub
            
        }
        
    }
    
}
