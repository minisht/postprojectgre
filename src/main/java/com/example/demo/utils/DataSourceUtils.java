package com.example.demo.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class DataSourceUtils {
	
	public static String executeJSONPath(String jsondata, String jsonpath) {
		
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsondata);
		return JsonPath.read(document, jsonpath).toString();
	}

}
