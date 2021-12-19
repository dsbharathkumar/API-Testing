package TS_001_V4_GET_REQUEST;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(utils.Listeners.class)

public class TC003_GET_Print_All_Headers {

	private static Response response = null;
	
	@BeforeClass
	public void beforeClass() {
		
		System.out.println("************* START OF TESTCASE - TC003_GET_Print_All_Headers **************");
		// Specify Base URI
		RestAssured.baseURI = "https://api.spacexdata.com/v4/launches/";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		response = httpRequest.request(Method.GET, "/latest");
	}
	
	@Test
	public void printAllHeaders() {
		
		//Capture All Headers
		Headers allHeaders = response.headers();
		
		for(Header header: allHeaders) {
			System.out.println(header.getName() + " -- " + header.getValue());
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("************* END OF TESTCASE - TC003_GET_Print_All_Headers **************");
	}
	
}
