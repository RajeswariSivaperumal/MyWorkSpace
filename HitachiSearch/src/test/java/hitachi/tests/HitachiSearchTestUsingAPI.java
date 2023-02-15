package hitachi.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HitachiSearchTestUsingAPI {

	@Test(enabled=true, description="Check if API request submitted successfully and response code 200 returned")
	public void searchUsingAPI()
	{
		RestAssured.baseURI="https://global.hitachi-solutions.com/";
		RequestSpecification request=RestAssured.given();

		request.header("Content-Type", "application/json");

		Map<String, String> param=new HashMap<String, String>();
		param.put("s", "chennai");
		param.put("post_type", "page");
		
		request.queryParams(param);
		
		Response response = request.get("");
		Assert.assertEquals(200, response.getStatusCode());
	}
}
