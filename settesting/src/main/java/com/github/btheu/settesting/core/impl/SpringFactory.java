package com.github.btheu.settesting.core.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.btheu.settesting.Factory;

public class SpringFactory implements Factory {

	private ClassPathXmlApplicationContext context;

	public SpringFactory(String configLocation){
		 context = new ClassPathXmlApplicationContext(configLocation);
	}
	
	
	public void inject(Object object) {
		context.getAutowireCapableBeanFactory().autowireBean(object);
	}

}
