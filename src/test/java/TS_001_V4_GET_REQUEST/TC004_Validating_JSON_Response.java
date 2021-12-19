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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.XLUtility;

@Listeners(utils.Listeners.class)

public class TC004_Validating_JSON_Response {

	private static Response response = null;
	
	@BeforeClass
	public void beforeClass() {
		
		System.out.println("************* START OF TESTCASE - TC004_GET_Validating_JSON_Response **************");
		// Specify Base URI
		RestAssured.baseURI = "https://api.spacexdata.com/v4/launches/";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		response = httpRequest.request(Method.GET, "/latest");
	}
	
	@Test(dataProvider = "apiDataProvider")
	public void validatingJsonResponse(String keyAttribute) {

		// Fetch Data with JsonPath
		JsonPath jsonPath =  response.jsonPath();
		
		switch(keyAttribute) {
		case "recovery_attempt":
			Assert.assertTrue(jsonPath.get(keyAttribute));
			break;
			
		case "links":
			Assert.assertNotNull(jsonPath.get(keyAttribute));
			break;
		
		case "window":
			Assert.assertNull(jsonPath.get(keyAttribute));
			break;
		
		case "success":
			Assert.assertTrue(jsonPath.get(keyAttribute));
			break;
			
		case "flight_number":
			int expected = jsonPath.get(keyAttribute);
			int actual = 141;
			Assert.assertEquals(expected, actual);
			break;
		}
	}

	
	@AfterClass
	public void afterClass() {
		System.out.println("************* END OF TESTCASE - TC004_Validating_JSON_Response **************");
	}
	
	@DataProvider(name="apiDataProvider")
	String [][] getData() throws IOException{
		
		// Read Data from Excel
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\externalData\\Sample-excel.xls" ;
		int rowNum = XLUtility.getRowCount(path, "Sheet2");
		int colNum = XLUtility.getCellCount(path, "Sheet2", rowNum);
		String data[][] = new String[rowNum][colNum];
		
		for(int i =1; i <= rowNum ; i++) {
			for(int j=0; j < colNum ; j++) {
				data[i-1][j] =XLUtility.getCellData(path, "Sheet2", i, j);
			}
		}
		
		return data;
	}
}
