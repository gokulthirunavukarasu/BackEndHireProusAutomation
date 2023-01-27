package com.Hireprous.Engine;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.restassured.response.Response;

public class RequestMethod extends BaseClass {
	public static Response POSTRequest(String URI, Object strJSON) throws IOException {
		logger.info("Inside Post Request call");
		Response res = given().accept("application/json").contentType("application/json")
				.header("pro-api-key", header).body(strJSON).when().post(URI);
		responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
		return res;
	}
	public static Response POSTRequest(String URI, Object strJSON, HashMap<String, String> pathparam) {
				logger.info("Inside Post Request call");
				Response res = given().filters(requestLoggingFilter,responseLoggingFilter).accept("application/json").contentType("application/json").pathParams(pathparam)
						.header("pro-api-key", header).body(strJSON).when().post(URI);
				responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
				return res;
			}

	public static Response GETObjectPathparam(String URI) {
		Response res = given().filters(requestLoggingFilter,responseLoggingFilter).accept("application/json").contentType("application/json")
				.header("pro-api-key", header).when().get(URI);
		responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
		return res;

	}
	
	public static Response GETObjectPathparam(String URI, HashMap<String, Object> pathparam) {
				logger.info("Inside get Request call");
				Response res = given().filters(requestLoggingFilter,responseLoggingFilter).accept("application/json").contentType("application/json").pathParams(pathparam)
						.header("pro-api-key", header).when().get(URI);
				responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
				return res;
		
			}
	public static Response PUTRequest(String URI, Object strJSON, HashMap<String, String> pathparam) {
				logger.info("Inside put Request call");
				Response res = given().filters(requestLoggingFilter,responseLoggingFilter).accept("application/json").contentType("application/json").pathParams(pathparam)
						.header("pro-api-key", header).body(strJSON).when().put(URI);
				responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
				return res;
			}
	public static Response DELETERequest(String URI) {
        logger.info("Inside Post Request call");
        Response res = given().filters(requestLoggingFilter,responseLoggingFilter).accept("application/json").contentType("application/json")
                .header("pro-api-key", header).when().delete(URI);
        responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
        return res;
           }
	public static Response DELETERequest(String URI, HashMap<String, String> pathParam) {
		logger.info("Inside Post Request call");
		Response res = given().filters(requestLoggingFilter,responseLoggingFilter).accept("application/json").contentType("application/json")
				.header("Authorization", header).pathParams(pathParam).when().delete(URI);
		responsetime = res.getTimeIn(TimeUnit.MILLISECONDS);
		return res;

	}
}
