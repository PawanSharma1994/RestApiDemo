package com.automation.rest_assured;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import com.automation.resources.PropertyReader;
import com.automation.resources.ReusableFunctions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestDemo4 {
	public static void main(String[] args) throws IOException {

		RestAssured.baseURI = PropertyReader.getProperty("BASE_URL");

		Response res = RestAssured.given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "AIzaSyB-sCvOtvGJUQO79lbca3-L-Ad3EcgWBb8").when().get("/maps/api/place/nearbysearch/json")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
				.header("Content-Encoding", "gzip").extract().response();

		JsonPath json = ReusableFunctions.rawToJson(res);
		int size = json.get("results.size()");
		System.out.println(size);
		for(int i=0;i<size;i++){
			System.out.println(json.get("results["+i+"].name"));
		}
		
		
	}
}
