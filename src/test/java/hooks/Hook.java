package hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.*;
import org.openqa.selenium.*;
import utilities.Driver;
import utilities.ExtentReportManager;

import java.util.Base64;

public class Hook {
    @Before
    public void setup(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());  // Test adını rapora ekle
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Ekran görüntüsünü Base64 formatında al
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            String base64Screenshot = Base64.getEncoder().encodeToString(screenshot);

            // Raporla birlikte ekran görüntüsü ekle
            ExtentReportManager.getTest()
                    .fail("Senaryo başarısız: " + scenario.getName(),
                            MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } else {
            ExtentReportManager.getTest()
                    .pass("Senaryo başarıyla tamamlandı: " + scenario.getName());
        }

        ExtentReportManager.flush();  // Raporu finalize et
        Driver.quitDriver();     // Browser'ı kapat
    }
}
