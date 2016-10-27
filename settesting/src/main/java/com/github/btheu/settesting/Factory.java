package com.github.btheu.settesting;

/**
 * 
 * The Java Factory for injecting tests beans (User, UseCase)
 * 
 * @author BTHE
 *
 */
public interface Factory extends TestInput {

    void inject(Object object);

    <T> T create(Class<T> objectClass);

}
