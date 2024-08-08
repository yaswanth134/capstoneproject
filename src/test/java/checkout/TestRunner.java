package checkout;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/feature/checkout.feature",
    glue = "checkout",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class TestRunner {
	
}