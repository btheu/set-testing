package com.github.btheu.settesting.core.impl;

import com.github.btheu.settesting.Factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VoidFactory implements Factory {

	public void inject(Object object) {
		log.debug("default inject bean does nothing");
	}

}
