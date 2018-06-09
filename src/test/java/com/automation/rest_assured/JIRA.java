package com.automation.rest_assured;

import java.io.IOException;

import com.automation.resources.PropertyReader;
import com.automation.resources.ReusableFunctions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JIRA {

	private static String sessionID;
	
	public void loginjira() throws IOException {

		RestAssured.baseURI = PropertyReader.getProperty("JIRA_HOST");

		Response res = RestAssured.given().header("Content-Type", "application/json")
				.body(PropertyReader.generateStringFromResource("JiraCredentials.json")).when()
				.post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response();
		
		
		JsonPath json = ReusableFunctions.rawToJson(res);
		sessionID = json.get("session.value");
		System.out.println(sessionID);

	}

	public void createIssue() throws IOException{
		
		Response res = RestAssured.given().header("Content-Type","application/json").header("Cookie","JSESSIONID="+sessionID).body(PropertyReader.generateStringFromResource("CreateIssue.json"))
		.when().post("/rest/api/2/issue").then().assertThat().statusCode(201).extract().response();
		
		JsonPath json = ReusableFunctions.rawToJson(res);
		System.out.println(json.get("id"));
	}
	
	public void addComment() throws IOException{
		RestAssured.given().header("Content-Type","application/json").header("Cookie","JSESSIONID="+sessionID).body(PropertyReader.generateStringFromResource("AddComment.json"))
		.when().post("/rest/api/2/issue/RES-8/comment").then().assertThat().statusCode(201);
	}
	
	
	public static void main(String[] args) throws IOException {

		JIRA jira = new JIRA();
		jira.loginjira();
//		jira.createIssue();
		jira.addComment();

	}

}
