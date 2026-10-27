package Shalwa.Nader.pom.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Map;

public class Drivers {

    private static WebDriver driver;

    public static WebDriver getDriver(DriversOptions driverOptions) {
        switch (driverOptions) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "profile.password_manager_leak_detection", false
                ));
                driver = new ChromeDriver(chromeOptions);
                break;

            case CHROME_IPHONE11:
                ChromeOptions mobileOptions = new ChromeOptions();
                mobileOptions.addArguments("--disable-save-password-bubble");
                mobileOptions.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "profile.password_manager_leak_detection", false
                ));
                driver = new ChromeDriver(mobileOptions);
                driver.manage().window().setSize(new Dimension(390, 844)); // iPhone 11 size
                break;

            case FIREFOX:
                driver = new FirefoxDriver();
                break;

            case SAFARI:
                driver = new SafariDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser type: " + driverOptions);
        }

        return driver;
    }
}
