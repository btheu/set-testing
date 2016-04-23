package com.github.btheu.settesting;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.github.btheu.settesting.core.ReportLine;
import com.github.btheu.settesting.core.impl.DefaultResultComparator;
import com.github.btheu.settesting.core.impl.DefaultTestCase;
import com.github.btheu.settesting.core.impl.InMemoryGridResultProvider;
import com.github.btheu.settesting.core.impl.InMemoryResultReport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SetTestingCase {

    protected List<Class<? extends Factory>> factories = new ArrayList<Class<? extends Factory>>();

    protected List<Class<? extends UseCase>> usecases = new ArrayList<Class<? extends UseCase>>();

    protected List<Class<? extends BusinessObject>> bos = new ArrayList<Class<? extends BusinessObject>>();

    private DefaultResultComparator comp;

    private InMemoryResultReport report;




    @Before
    public void before(){
        log.info("before");
        factories.clear();
        usecases.clear();
        bos.clear();

        comp = new DefaultResultComparator();
        InMemoryGridResultProvider gridResultProvider = new InMemoryGridResultProvider();
        report = new InMemoryResultReport();

        comp.setGridResultProvider(gridResultProvider);
        comp.setResultReport(report);
    }

    @After
    public void after() throws InstantiationException, IllegalAccessException{
        log.info("after");

        for (Class<? extends UseCase> usecaseClass : usecases) {
            for (Class<? extends BusinessObject> boClass : bos) {


                UseCase usecase = usecaseClass.newInstance();

                BusinessObject bo = boClass.newInstance();

                bo.create();
                
                Result result = usecase.execute();
                
                bo.remove();

                comp.compare(result, new DefaultTestCase(usecase,bo));
                
            }
        }

        List<ReportLine> lines = report.getAllLines();
        for (ReportLine line : lines) {
            log.info("{}",line.success());
        }
        
    }

    /*
	ResultComparator comparator = new SimpleJSONResultComparator("/tmp/tests/prono/");

	@Test
	public void simpleUES() throws InstantiationException, IllegalAccessException{

		SpringFactory factory = new SpringFactory();

		Class<? extends User>[] array = (Class<? extends User>[]) new Class<?>[]{ Simple1User.class, AnonymousUser.class, Simple1User.class };

		for(Class<? extends User> clazz : array ){

			log.info("Test with [{}]",clazz.getSimpleName());

			User user = clazz.newInstance();
			factory.inject(user);

			user.create();

			user.validate();

			user.authenticate();



			GetTousMatchUseCase useCase = new GetTousMatchUseCase();
			factory.inject(useCase);


			execute(user, useCase);

			user.invalidate();

			user.remove();
		}

		comparator.printReport();

	}

	private void execute(User user, UseCase useCase) {
		try {
			Result result = useCase.execute();

			compareResult(user,useCase,result);
		} catch (Exception e) {
			compareResult(user,useCase, new ThrowableResult(e));
		}
	}

	private void compareResult(User user, UseCase useCase, Result execute) {
		comparator.compare(user,useCase,execute);
	}
     */
}
