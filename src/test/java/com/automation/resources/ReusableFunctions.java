package com.automation.resources;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableFunctions {

	public static XmlPath rawToXml(Response response){
		String resString = response.asString();
		System.out.println(resString);
		XmlPath xmlPath = new XmlPath(resString);
		return xmlPath;
	}
	
	public static JsonPath rawToJson(Response response){
		String resString = response.asString();
		System.out.println(resString);
		JsonPath jsonPath = new JsonPath(resString);
		return jsonPath;
	}
	
	
}
