package com.github.btheu.settesting;

public interface UseCase extends TestInput {

    Result execute(BusinessObject...businessObjects);

}
