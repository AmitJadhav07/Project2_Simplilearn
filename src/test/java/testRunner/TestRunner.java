package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/features/Swiggy_Assignment2.feature", glue="stepDefinition", plugin= {"pretty", "html:target/cucumber-reports.html","json:target/Cucumber.json","junit:target/Cucumber.xml","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"} )	

public class TestRunner extends AbstractTestNGCucumberTests {	
	
	
}	
