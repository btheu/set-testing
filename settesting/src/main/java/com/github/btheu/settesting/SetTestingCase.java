package com.github.btheu.settesting;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.github.btheu.settesting.core.ReportLine;
import com.github.btheu.settesting.core.impl.DefaultResultValidator;
import com.github.btheu.settesting.core.impl.DefaultTestCase;
import com.github.btheu.settesting.core.impl.InMemoryGridResultProvider;
import com.github.btheu.settesting.core.impl.InMemoryReport;
import com.github.btheu.settesting.core.impl.ThrowableResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Deprecated
public class SetTestingCase {

    protected List<Class<? extends Factory>> factories = new ArrayList<Class<? extends Factory>>();

    protected List<Class<? extends UseCase>> usecases = new ArrayList<Class<? extends UseCase>>();

    protected List<Class<? extends BusinessObject>> bos = new ArrayList<Class<? extends BusinessObject>>();

    private DefaultResultValidator validator;

    private InMemoryReport report;




    @Before
    public void before(){
        log.info("before");
        factories.clear();
        usecases.clear();
        bos.clear();

        validator = new DefaultResultValidator();
        InMemoryGridResultProvider gridResultProvider = new InMemoryGridResultProvider();
        report = new InMemoryReport();

        validator.setGridResultProvider(gridResultProvider);
        validator.setReport(report);
    }

    @After
    public void after() throws InstantiationException, IllegalAccessException{
        log.info("after");

        for (Class<? extends UseCase> usecaseClass : usecases) {
            for (Class<? extends BusinessObject> boClass : bos) {


                UseCase usecase = usecaseClass.newInstance();

                BusinessObject bo = boClass.newInstance();

                bo.create();
                
                Result result = execute(usecase, bo);
                
                bo.remove();

                validator.compare(result, new DefaultTestCase(usecase,bo));
                
            }
        }

        List<ReportLine> lines = report.getAllLines();
        for (ReportLine line : lines) {
            log.info("{}",line.success());
        }
        
    }

    private Result execute(UseCase usecase, BusinessObject bo) {
        try {
            Result result = usecase.execute(bo);
            return result;
        } catch (Exception e) {
            return new ThrowableResult(e);
        }
    }
}
