package com.github.btheu.settesting;

import java.util.List;

import org.junit.Test;

import com.github.btheu.settesting.core.ReportLine;
import com.github.btheu.settesting.core.ResultReport;
import com.github.btheu.settesting.core.impl.DefaultResultComparator;
import com.github.btheu.settesting.core.impl.DefaultTestCase;
import com.github.btheu.settesting.core.impl.InMemoryGridResultProvider;
import com.github.btheu.settesting.core.impl.InMemoryResultReport;
import com.github.btheu.settesting.core.impl.PrimitiveValueResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultComparatorTest {

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        
        DefaultResultComparator comp = new DefaultResultComparator();
        InMemoryGridResultProvider gridResultProvider = new InMemoryGridResultProvider();
        InMemoryResultReport report = new InMemoryResultReport();
        
        comp.setGridResultProvider(gridResultProvider);
        comp.setResultReport(report);
        
        Class<SimpleUseCase> useCaseClass = SimpleUseCase.class;
        
        Class<SimpleObject> businessObjectClass = SimpleObject.class;
        
        SimpleUseCase useCase = useCaseClass.newInstance();
        
        SimpleObject businessObject = businessObjectClass.newInstance();
        
        Result r1 = new PrimitiveValueResult(1);
        
        Result r2 = new PrimitiveValueResult(2);
        
        Result r3 = new PrimitiveValueResult(3);
        
        comp.compare(r1,new DefaultTestCase(useCase, businessObject));
        comp.compare(r1,new DefaultTestCase(useCase, businessObject));
        comp.compare(r2,new DefaultTestCase(businessObject, useCase));
        comp.compare(r2,new DefaultTestCase(businessObject, useCase));
        
        
        comp.compare(r3,new DefaultTestCase(useCase, businessObject));
        comp.compare(r3,new DefaultTestCase(businessObject, useCase));
        
        
        ResultReport resultReport = comp.getReport();
        
        List<ReportLine> lines = resultReport.getAllLines();
        for (ReportLine line : lines) {
            log.info("{}",line.success());
        }
    }
 
    static class SimpleUseCase implements UseCase {

        public Result execute() {
            return null;
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
