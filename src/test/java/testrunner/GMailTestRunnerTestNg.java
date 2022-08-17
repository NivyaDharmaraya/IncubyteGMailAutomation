package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class) // for Junit
@CucumberOptions(
		features="src/test/java/features/GMailfeatures.feature",
        glue={"stepDefinitions"},
        plugin = {"pretty","html:target/cucumber-reports1.html"},
       // tags = "@smoke",
        monochrome = true
        )
	//"json:target/cucumber.json",
public class GMailTestRunnerTestNg {

}
