package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqSpecBase;
	public static ResponseSpecification resSpec;
	
	public RequestSpecification getReqSpecBase() throws IOException
	{
		if(reqSpecBase == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			reqSpecBase = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", "qaclick123").build();
			
			return reqSpecBase;			
		}
		return reqSpecBase;
	}
	
	public ResponseSpecification getResSpec()
	{
		resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectHeader("Server", "Apache/2.4.52 (Ubuntu)")
				.build();
		return resSpec;
	}
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\abhik\\eclipse-workspace\\APITestFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPathValue(Response response,String path) {
		String responseStr = response.asString();
		JsonPath responseJson = new JsonPath(responseStr);
		return responseJson.get(path).toString();
	}

}
