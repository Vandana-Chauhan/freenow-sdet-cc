package com.freenow.ste.cc.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	private RequestSpecification request;
	private RequestSpecification resquestSpec;
	private Response response;
	
	public RequestSpecification requestSpecification()  {

		if (request == null) {
			
			request = new RequestSpecBuilder().setBaseUri(getGlobalvalue("baseURl"))
					.build();
			
			return request;
		}

		return request;
	}

	public static String getGlobalvalue(String key) {
		Properties prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(
					"src/test/resources/global.properties");
			
			prop.load(fis);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		

		return prop.getProperty(key);

	}

	public static String getJsonValueOfKey(Response response, String key) {

		JsonPath js = new JsonPath(response.asString());

		return js.get(key).toString();
	}

	/**
	 * Trigger get request Accept two parameter: key, value Return the
	 * RequestSpecification object
	 * 
	 * @throws IOException
	 */
	public RequestSpecification getRequestSpec(String Key, String value){
		resquestSpec = given().spec(requestSpecification()).queryParam(Key, value).log().all();

		return resquestSpec;

	}

	/**
	 * Trigger get response Accept one parameter: url Return the Response returned
	 * from the api
	 * 
	 * @throws IOException
	 */
	public Response getResponse(RequestSpecification resquestSpec, ResourcePath resource) {
		response = resquestSpec.when().get(resource.getResource()).then().log().all().extract().response();

		return response;

	}

}
