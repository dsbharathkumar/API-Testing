package TS_001_V4_GET_REQUEST;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@Listeners(utils.Listeners.class)

public class TC001_GET_Request {

	private static Response response = null;

	@BeforeClass
	public void beforeClass() {
		
		System.out.println("************* START OF TESTCASE - TC001_GET_Request **************");
		// Specify Base URI
		RestAssured.baseURI = "https://api.spacexdata.com/v4/launches/";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		response = httpRequest.request(Method.GET, "/latest");
	}

	@Test
	public void getLatestLaunchDetails() {

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:  " + responseBody);

	}

	@Test
	public void verifyStatusCode() {
		
		// Status Code validation
		int statusCode = response.getStatusCode();
		System.out.println("status code is " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void verifyStatusLine() {

		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println("status line is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void verifyResponseTime() {
		
		// Response Time verification
		// obtain ValidatableResponse type
	      ValidatableResponse v = response.then();
	      v.time(Matchers.lessThan(2000L));
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("************* END OF TESTCASE - TC001_GET_Request **************");
	}
}
