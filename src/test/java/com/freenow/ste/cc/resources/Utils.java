package com.freenow.ste.cc.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	 RequestSpecification request;
	 RequestSpecification resquestSpec;
	 Response response;
	
	public  RequestSpecification requestSpecification() {

		if (request == null) {
			
			
			
				PrintStream log;
				try {
					log = new PrintStream(new FileOutputStream("logging.txt"));
				
			
		
			request = new RequestSpecBuilder().setBaseUri(getGlobalvalue("baseURl")).addFilter(RequestLoggingFilter.logRequestTo(log))
					.build();
			
			return request;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return request;
	}

	public  String getGlobalvalue(String key) {
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

//	public static String getJsonValueOfKey(Response response, String key) {
//
//		JsonPath js = new JsonPath(response.asString());
//
//		return js.get(key).toString();
//	}

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
