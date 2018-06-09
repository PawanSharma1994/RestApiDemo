package com.automation.rest_assured;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import com.automation.resources.PropertyReader;
import com.automation.resources.ReusableFunctions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class RestDemo3 {

	public static void main(String[] args) throws IOException {

		RestAssured.baseURI = PropertyReader.getProperty("BASE_URL");

		// Grab the response
		Response res = RestAssured.given().queryParam("key", PropertyReader.getProperty("API_KEY"))
				.body(PropertyReader.generateStringFromResource("PlaceAddRequest.xml")).when()
				.post("/maps/api/place/add/xml").then().assertThat().contentType(ContentType.XML).and().statusCode(200)
				.extract().response();
		XmlPath xmlPath = ReusableFunctions.rawToXml(res);
		
		System.out.println(xmlPath.get("PlaceAddResponse.place_id"));
	}

}
