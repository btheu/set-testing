package com.github.btheu.settesting.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.btheu.settesting.Result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"message","throwClass"})
public class ThrowableResult implements Result {

	private Throwable throwable;
    private Class<? extends Throwable> throwClass;
    private String message;

	public ThrowableResult(Throwable throwable) {
		this.throwable = throwable;
		throwClass = throwable.getClass();
		message = throwable.getMessage();
	}

	public Map<String, String> getMetas() {

		Map<String, String> metas = new HashMap<String, String>();

		metas.put("class", throwClass.getSimpleName());
		metas.put("message", message);

		return metas;
	}

	public boolean isCorrect(Map<String, String> previousResultMetas) {

		return throwable.getMessage().equals(previousResultMetas.get("message"))
				&& throwable.getClass().getSimpleName().equals(previousResultMetas.get("class"));

	}

}
