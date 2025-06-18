package utilities;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;

import java.time.*;

public class Driver {
    private Driver() {}

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigurationReader.getProperty("browser");

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
//                    chromeOptions.addArguments("--headless=new"); // yeni headless modu
//                    chromeOptions.addArguments("--disable-gpu");
//                    chromeOptions.addArguments("--window-size=1920,1080");
//                    chromeOptions.addArguments("--remote-allow-origins=*");// Headless test için opsiyonel
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    ChromeOptions defaultOptions = new ChromeOptions();
                    defaultOptions.addArguments("--headless=new");
                    defaultOptions.addArguments("--window-size=1920,1080");
                    defaultOptions.addArguments("--disable-gpu");
                    defaultOptions.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(defaultOptions);
                    break;
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close(); // Sadece aktif sekmeyi kapatır
            driver = null;
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit(); // Tüm sekmeleri ve driver'ı kapatır
            driver = null;
        }
    }
}
