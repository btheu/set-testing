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

/**
 * 
 * @author theunissenb
 * @deprecated use SetTestingRunner
 *
 */
@Slf4j
@Deprecated
public class SetTestingCase {

    protected List<Class<? extends Factory>> factories = new ArrayList<Class<? extends Factory>>();

    protected List<Class<? extends UseCase>> usecases = new ArrayList<Class<? extends UseCase>>();

    protected List<Class<? extends BusinessObject>> bos = new ArrayList<Class<? extends BusinessObject>>();

    private DefaultResultValidator validator;

    private InMemoryReport report;

    @Before
    public void before() {
        log.info("before");
        this.factories.clear();
        this.usecases.clear();
        this.bos.clear();

        this.validator = new DefaultResultValidator();
        InMemoryGridResultProvider gridResultProvider = new InMemoryGridResultProvider();
        this.report = new InMemoryReport();

        this.validator.setGridResultProvider(gridResultProvider);
        this.validator.setReport(this.report);
    }

    @After
    public void after() throws InstantiationException, IllegalAccessException {
        log.info("after");

        for (Class<? extends UseCase> usecaseClass : this.usecases) {
            for (Class<? extends BusinessObject> boClass : this.bos) {

                UseCase usecase = usecaseClass.newInstance();

                BusinessObject bo = boClass.newInstance();

                bo.create();

                Result result = this.execute(usecase, bo);

                bo.remove();

                this.validator.compare(result, new DefaultTestCase(usecase, bo));

            }
        }

        List<ReportLine> lines = this.report.getAllLines();
        for (ReportLine line : lines) {
            log.info("{}", line.success());
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
