package com.github.btheu.settesting;

import org.junit.Test;

import com.github.btheu.settesting.core.impl.DefaultResultComparator;
import com.github.btheu.settesting.core.impl.GridResultProvider;
import com.github.btheu.settesting.core.impl.InMemoryGridResultProvider;
import com.github.btheu.settesting.core.impl.PrimitiveValue;

public class ResultComparatorTest {

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        
        DefaultResultComparator comp = new DefaultResultComparator();
        GridResultProvider gridResultProvider = new InMemoryGridResultProvider();
        
        comp.setGridResultProvider(gridResultProvider);
        
        Class<SimpleUseCase> useCaseClass = SimpleUseCase.class;
        
        Class<SimpleObject> businessObjectClass = SimpleObject.class;
        
        SimpleUseCase useCase = useCaseClass.newInstance();
        
        SimpleObject businessObject = businessObjectClass.newInstance();
        
        Result r1 = new PrimitiveValue(1);
        
        Result r2 = new PrimitiveValue(2);
        
        Result r3 = new PrimitiveValue(3);
        
        comp.compare(r1, useCase, businessObject);
        
        comp.compare(r1, useCase, businessObject);
        
        comp.compare(r2, businessObject, useCase);
        
        comp.compare(r2, businessObject, useCase);
        
        
        comp.compare(r3, useCase, businessObject);
        comp.compare(r3, businessObject, useCase);
        
    }
 
    static class SimpleUseCase implements UseCase {

        public Result execute() {
            return null;
        }
        
    }
    
    static class SimpleObject implements BusinessObject {
        
    }
    
}
