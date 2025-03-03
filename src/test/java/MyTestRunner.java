



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


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

public class MyTestRunner {
}
