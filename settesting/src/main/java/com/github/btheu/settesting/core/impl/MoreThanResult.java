package com.github.btheu.settesting.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.btheu.settesting.Result;

import lombok.Data;

@Data
public class MoreThanResult implements Result {

	private int currentValue;
	private int thresholdValue;

	public MoreThanResult(int currentValue) {
		this.currentValue = currentValue;
	}

	public Map<String, String> getMetas() {
		Map<String, String> metas = new HashMap<String, String>();
		
		metas.put("thresholdValue", Integer.toString(thresholdValue));
		
		return metas;
	}

	public boolean isCorrect(Map<String, String> previousResultMetas) {
		
		
		
		return false;
	}

}
