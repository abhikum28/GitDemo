package stepDefinitions;

import static io.restassured.RestAssured.*;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification reqSpec;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	static String place_Id;
	
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {	
		reqSpec = given().spec(getReqSpecBase()).body(testData.addPlacePayload(name, language, address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		// enum constructor will be called with valueOf method and pass resource as parameter 
		APIResources apiResource = APIResources.valueOf(resource);
		
		System.out.println(apiResource.getResource());
		
		if(httpMethod.equalsIgnoreCase("post")) {
			response = reqSpec.when().post(apiResource.getResource());
		}
		else if(httpMethod.equalsIgnoreCase("get")) {
			response = reqSpec.when().get(apiResource.getResource());		
		}
	}
	
	@Then("the API call is succes with status code {int}")
	public void the_api_call_is_succes_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
		System.out.println("A new line added by user Y");
		System.out.println("A new line added by user X");
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		assertEquals(getJsonPathValue(response, key), expectedValue);
		System.out.println("Another line added by user X in response body function");
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
		place_Id = getJsonPathValue(response, "place_id");
		reqSpec = given().spec(getReqSpecBase())
				.queryParam("place_id", place_Id);
		user_calls_with_http_request(resource, "GET");
		String actName = getJsonPathValue(response, "name");
		Assert.assertEquals(actName, expName);
		System.out.println("Develop branch change new line added by user Y");
		System.out.println("Develop branch change new line added by user X");
	}

	@Given("Delete Place payload")
	public void delete_place_payload() throws IOException {
		reqSpec = given().spec(getReqSpecBase())
				.body(testData.delPlacePayload(place_Id));
		System.out.println("Develop branch change line added by user X");
	}
	
}
