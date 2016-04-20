package com.github.btheu.settesting;

import java.util.Map;

public interface Result {

	Map<String, String> getMetas();
	
	boolean isCorrect(Map<String, String> previousResultMetas);
	
}
