package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import resources.CucumberReportBackupUtility;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//write a code that will give you place_id
		//Execute only when place_id is null
		if(StepDefinition.place_Id == null) {
			StepDefinition sd = new StepDefinition();
			sd.add_place_payload_with("Hamilton Homes", "English", "Bengaluru EC");
			sd.user_calls_with_http_request("AddPlaceAPI", "Post");
			sd.verify_place_id_created_maps_to_using("Hamilton Homes", "GetPlaceAPI");				
		}
	}
	
    @AfterAll
    public static void backupReports() throws IOException {
        CucumberReportBackupUtility.backupReports();
    }	
	
}
