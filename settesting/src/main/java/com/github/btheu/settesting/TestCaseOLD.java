package com.github.btheu.settesting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestCaseOLD {
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
