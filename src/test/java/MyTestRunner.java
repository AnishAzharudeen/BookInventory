



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\mhdaz\\IdeaProjects\\Playwrightjava\\src\\test\\resources\\features",
        glue = {"steps","hooks"},
        tags = "@Test",
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-reports/Cucumber.html",
                "timeline:target/cucumber-reports/CucumberTimeline"}
)
@ConfigurationParameter(key = "cucumber.plugin", value = "io.qameta.allure.cucumber7jvm.Allure7jvmp," +"pretty,html:target/cucumber-reports")
public class MyTestRunner {
}
