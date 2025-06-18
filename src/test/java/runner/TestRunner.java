package runner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
        features = "src/test/resources/features",  // Feature dosyalarının yolu
        glue = {"stepdefinitions", "hooks"},       // Step definition ve hooks path
        dryRun = false,                            // true: eksik step kontrolü, false: test çalışır
        monochrome = true                          // konsol çıktısı okunabilir olsun
)

public class TestRunner {
}
