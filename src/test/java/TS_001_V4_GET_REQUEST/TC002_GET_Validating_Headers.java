package TS_001_V4_GET_REQUEST;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.XLUtility;

@Listeners(utils.Listeners.class)

public class TC002_GET_Validating_Headers {

	private static Response response = null;
	
	@BeforeClass
	public void beforeClass() {
		
		System.out.println("************* START OF TESTCASE - TC002_GET_Validating_Headers **************");
		// Specify Base URI
		RestAssured.baseURI = "https://api.spacexdata.com/v4/launches/";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		response = httpRequest.request(Method.GET, "/latest");
	}
	
	@Test(dataProvider = "apiDataProvider")
	public void validateHeaders(String headers, String value) {
		
		// Capture details of headers from response
		
		String headerValue = response.header(headers);
		
		switch(headers) {
		case "Access-Control-Allow-Origin": 
			Assert.assertEquals(headerValue, "*");
			break;
			
		case "Alt-Svc":
			Assert.assertEquals(headerValue, "h3=\":443\"; ma=2592000,h3-29=\":443\"; ma=2592000");
			break;
			
		case "Access-Control-Expose-Headers":
			Assert.assertEquals(headerValue, "spacex-api-cache,spacex-api-response-time");
			break;	
			
		case "Cache-Control":
			Assert.assertEquals(headerValue, "max-age=20");
			break;	
		
		case "Content-Encoding":
			Assert.assertEquals(headerValue, "gzip");
			break;
			
		case "Content-Type":
			Assert.assertEquals(headerValue, "application/json; charset=utf-8");
			break;
		
		case "Expect-Ct":
			Assert.assertEquals(headerValue, "max-age=0");
			break;
		
		case "Referrer-Policy":
			Assert.assertEquals(headerValue, "no-referrer");
			break;	
		
		case "Server":
			Assert.assertEquals(headerValue, "Caddy");
			break;	
		
		case "Spacex-Api-Cache":
			Assert.assertEquals(headerValue, "HIT");
			break;	
		
		case "X-Frame-Options":
			Assert.assertEquals(headerValue, "SAMEORIGIN");
			break;
		
		case "Content-Length":
			Assert.assertEquals(headerValue, "930");
			break;
		}
	}
	
	@DataProvider(name="apiDataProvider")
	String [][] getData() throws IOException{
		
		// Read Data from Excel
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\externalData\\Sample-excel.xls" ;
		int rowNum = XLUtility.getRowCount(path, "Sheet1");
		int colNum = XLUtility.getCellCount(path, "Sheet1", rowNum);
		String data[][] = new String[rowNum][colNum];
		
		for(int i =1; i <= rowNum ; i++) {
			for(int j=0; j < colNum ; j++) {
				data[i-1][j] =XLUtility.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return data;
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("************* END OF TESTCASE - TC002_GET_Validating_Headers **************");
	}
}
