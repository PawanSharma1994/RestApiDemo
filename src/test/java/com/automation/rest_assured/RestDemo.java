package com.automation.rest_assured;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import com.automation.resources.PropertyReader;
import io.restassured.RestAssured;

public class RestDemo {

	public static void main(String[] args) throws IOException {

		RestAssured.baseURI = PropertyReader.getProperty("BASE_URL");

		RestAssured.given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "AIzaSyB-sCvOtvGJUQO79lbca3-L-Ad3EcgWBb8").log().all().when().get("/maps/api/place/nearbysearch/json")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and()
				.body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
				.header("Content-Encoding", "gzip");

	}

}
