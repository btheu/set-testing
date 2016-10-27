package com.github.btheu.settesting.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.btheu.settesting.Factory;

public class SpringFactory implements Factory {

	private ClassPathXmlApplicationContext context;

	public SpringFactory() {
		context = new ClassPathXmlApplicationContext("classpath:testApplicationContext.xml");
	}

	public void inject(Object object) {
		context.getAutowireCapableBeanFactory().autowireBean(object);
	}

	public <T> T create(Class<T> objectClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
