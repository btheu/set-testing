package com.github.btheu.settesting;

import org.junit.runner.RunWith;

import com.github.btheu.settesting.ResultComparatorTest.NewObject1;
import com.github.btheu.settesting.ResultComparatorTest.ValidObject1;
import com.github.btheu.settesting.ResultComparatorTest.ValidateUseCase;
import com.github.btheu.settesting.SetTestingRunner.BusinessObjects;
import com.github.btheu.settesting.SetTestingRunner.UseCases;


@UseCases(ValidateUseCase.class)
@BusinessObjects({NewObject1.class,NewObject1.class,ValidObject1.class,ValidObject1.class})
@RunWith(SetTestingRunner.class)
public class SetTestingRunnerTest {

}
