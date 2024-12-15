package cucumber.Options;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.After;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import resources.CucumberReportBackupUtility;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features"
, glue = {"stepDefinitions"}
, plugin = "json:target/jsonReports/cucumber-report.json"
//, tags = "(@DeletePlace)"
)
public class TestRunner {



}
