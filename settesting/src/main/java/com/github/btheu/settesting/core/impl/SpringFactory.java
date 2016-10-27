package com.github.btheu.settesting.core.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.btheu.settesting.Factory;

public class SpringFactory implements Factory {

    protected ApplicationContext context;
    protected String configLocation;

    public SpringFactory() {
        this.context = this.createApplicationContext();
    }

    public SpringFactory(String configLocation) {
        this.configLocation = configLocation;
        this.context = this.createApplicationContext();
    }

    public void inject(Object object) {
        this.context.getAutowireCapableBeanFactory().autowireBean(object);
    }

    public <T> T create(Class<T> objectClass) {
        return this.context.getAutowireCapableBeanFactory().createBean(objectClass);
    }

    protected ApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext(this.configLocation);
    }

}
