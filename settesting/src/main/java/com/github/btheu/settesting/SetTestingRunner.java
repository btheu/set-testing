package com.github.btheu.settesting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.springframework.util.CollectionUtils;

import com.github.btheu.settesting.core.impl.DefaultResultValidator;
import com.github.btheu.settesting.core.impl.DefaultTestCase;
import com.github.btheu.settesting.core.impl.InMemoryGridResultProvider;
import com.github.btheu.settesting.core.impl.InMemoryReport;
import com.github.btheu.settesting.core.impl.ThrowableResult;
import com.github.btheu.settesting.core.impl.VoidFactory;

public class SetTestingRunner extends Suite {

	private List<Runner> runners;

	private Class<?> testClass;

	private static final List<Runner> NO_RUNNERS = Collections.<Runner>emptyList();

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface Factories {
		Class<? extends Factory>[] value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface UseCases {
		Class<? extends UseCase>[] value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface BusinessObjects {
		Class<? extends BusinessObject>[] value();
	}

	protected List<Class<? extends Factory>> factories = new ArrayList<Class<? extends Factory>>();

	protected List<Class<? extends UseCase>> usecases = new ArrayList<Class<? extends UseCase>>();

	protected List<Class<? extends BusinessObject>> bos = new ArrayList<Class<? extends BusinessObject>>();

	public SetTestingRunner(Class<?> klass) throws InitializationError {
		super(klass, NO_RUNNERS);

		this.testClass = klass;

		Factories aFactories = klass.getAnnotation(Factories.class);
		if (aFactories != null) {
			addAll(factories, aFactories.value());
		} else {
			factories.add(VoidFactory.class);
		}

		UseCases aUseCases = klass.getAnnotation(UseCases.class);
		if (aUseCases != null) {
			addAll(usecases, aUseCases.value());
		}

		BusinessObjects aBusinessObjects = klass.getAnnotation(BusinessObjects.class);
		if (aBusinessObjects != null) {

			addAll(bos, aBusinessObjects.value());
		}

		this.runners = new ArrayList<Runner>();

		final DefaultResultValidator validator = new DefaultResultValidator();
		InMemoryGridResultProvider gridResultProvider = new InMemoryGridResultProvider();
		InMemoryReport report = new InMemoryReport();

		validator.setGridResultProvider(gridResultProvider);
		validator.setReport(report);

		int seq = 1;

		for (final Class<? extends Factory> factoryClass : factories) {

			for (final Class<? extends UseCase> usecaseClass : usecases) {
				for (final Class<? extends BusinessObject> boClass : bos) {

					final int runnerId = seq++;

					this.runners.add(new Runner() {

						@Override
						public void run(RunNotifier notifier) {

							try {

								notifier.fireTestStarted(this.getDescription());

								Factory factory = factoryClass.newInstance();

								UseCase usecase = usecaseClass.newInstance();

								factory.inject(usecase);

								BusinessObject businessObject = boClass.newInstance();

								factory.inject(businessObject);

								businessObject.create();

								Result result = execute(usecase, businessObject);

								businessObject.remove();

								validator.validate(result, new DefaultTestCase(usecase, businessObject));

								notifier.fireTestFinished(this.getDescription());

							} catch (InstantiationException e) {
								notifier.fireTestFailure(new Failure(this.getDescription(), e));
							} catch (IllegalAccessException e) {
								notifier.fireTestFailure(new Failure(this.getDescription(), e));
							} catch (ValidationException e) {
								notifier.fireTestFailure(new Failure(this.getDescription(), e));
							}
						}

						@Override
						public Description getDescription() {
							Description desc = Description.createTestDescription(testClass,
									usecaseClass.getSimpleName() + " : " + boClass.getSimpleName() + " " + runnerId);
							return desc;
						}

						private Result execute(UseCase usecase, BusinessObject bo) {
							try {
								Result result = usecase.execute(bo);
								return result;
							} catch (Exception e) {
								return new ThrowableResult(e);
							}
						}

					});

				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private <T> void addAll(List<Class<? extends T>> list, Class<? extends T>[] items) {
		list.addAll(CollectionUtils.arrayToList(items));
	}

	@Override
	protected List<Runner> getChildren() {
		return runners;
	}

}
