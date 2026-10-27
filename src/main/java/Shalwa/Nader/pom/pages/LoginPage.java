package Shalwa.Nader.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private static final String password = "secret_sauce";

    private final By errorMessage = By.className("error-message-container");
    private final By pageUserName = By.id("user-name");
    private final By pagePassword = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        driver.get("https://www.saucedemo.com/");
        this.driver = driver;
    }

    // General login method: returns HomePage if successful
    public HomePage loginToPage(String userName) {
        driver.findElement(pageUserName).clear();
        driver.findElement(pagePassword).clear();

        driver.findElement(pageUserName).sendKeys(userName);
        driver.findElement(pagePassword).sendKeys(password);

        // Wait until login button is clickable instead of Thread.sleep
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();

        return new HomePage(driver);
    }

    public HomePage goToHomePage() {
        return loginToPage("standard_user");
    }

    public HomePage goToProblemHomePage() {
        return loginToPage("problem_user");
    }

    public HomePage performanceGlitchedUserLogin() {
        return loginToPage("performance_glitch_user");
    }

    public String lockedOutLogin() {
        driver.findElement(pageUserName).clear();
        driver.findElement(pagePassword).clear();
        driver.findElement(pageUserName).sendKeys("locked_out_user");
        driver.findElement(pagePassword).sendKeys(password);

        driver.findElement(loginButton).click();
        return driver.findElement(errorMessage).getText();
    }

    public HomePage errorUserLogin() {
        return loginToPage("error_user");
    }

    public HomePage visualUserLogin() {
        return loginToPage("visual_user");
    }

    public String invalidLogin() {
        driver.findElement(pageUserName).clear();
        driver.findElement(pagePassword).clear();
        driver.findElement(pageUserName).sendKeys("incorrect_user");
        driver.findElement(pagePassword).sendKeys(password);

        driver.findElement(loginButton).click();
        return driver.findElement(errorMessage).getText();
    }
}
