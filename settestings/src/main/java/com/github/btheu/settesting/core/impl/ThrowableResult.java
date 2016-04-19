package com.github.btheu.settesting.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.btheu.settesting.Result;

import lombok.Data;

@Data
public class ThrowableResult implements Result {

	private Throwable throwable;

	public ThrowableResult(Throwable throwable) {
		this.throwable = throwable;
	}

	public Map<String, String> getMetas() {

		Map<String, String> metas = new HashMap<String, String>();

		metas.put("message", throwable.getMessage());
		metas.put("class", throwable.getClass().getSimpleName());

		return metas;
	}

	public boolean isCorrect(Map<String, String> previousResultMetas) {

		return throwable.getMessage().equals(previousResultMetas.get("message"))
				&& throwable.getClass().getSimpleName().equals(previousResultMetas.get("class"));

	}

}
