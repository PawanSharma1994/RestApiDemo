package com.automation.rest_assured;

import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import com.automation.resources.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestDemo2 {

	public static void main(String[] args) throws IOException {
		String body = "{" + "\"location\": {" + "\"lat\": -33.8669710," + "\"lng\": 151.1958750" + "},"
				+ "\"accuracy\": 50," + "\"name\": \"Google Shoes!\"," + "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," + "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\"," + "\"language\": \"en-AU\"" + "}";

		RestAssured.baseURI = PropertyReader.getProperty("BASE_URL");

		// Grab the response
		Response response = RestAssured.given().queryParam("key",PropertyReader.getProperty("API_KEY")).body(body)
				.when().post("/maps/api/place/add/json").then().assertThat().body("status", equalTo("OK")).and()
				.statusCode(200).and().contentType(ContentType.JSON).extract().response();

		// Grab the place_id from response
		String res = response.asString();
		System.out.println(res);
		JsonPath json = new JsonPath(res);
		String placeid = json.get("place_id");
		System.out.println("Placeid is : "+placeid);

		// place the id in post delete request
		RestAssured.given().queryParam("key", "AIzaSyB-sCvOtvGJUQO79lbca3-L-Ad3EcgWBb8")
				.body("{" + "\"place_id\": \""+placeid+"\"" + "}").when().post("/maps/api/place/delete/json").then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"));
	}

}
