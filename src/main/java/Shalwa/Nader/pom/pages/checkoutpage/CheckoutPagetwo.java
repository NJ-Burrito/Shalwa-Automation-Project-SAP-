package Shalwa.Nader.pom.pages.checkoutpage;

import Shalwa.Nader.pom.pages.NavigationPage;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.CartPage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPagetwo extends NavigationPage {

    private WebDriver driver;
    private static final By checkoutBtn = By.id("checkout");
    private static final By firstName = By.id("first-name");
    private static final By lastName = By.id("last-name");
    private static final By postalCode = By.id("postal-code");
    private static final By continueBtn = By.id("continue");

    private static final By cancelButton = By.id("cancel");
    private static final By finishButton = By.id("finish");
    private static final By cartItem = By.className("cart_item");
    private static final By burgerMenuBtn = By.id("react-burger-menu-btn");
    private static final By allItemsSidebarLink = By.id("inventory_sidebar_link");
    private static final By aboutSidebarLink = By.id("about_sidebar_link");
    private static final By logoutSidebarLink = By.id("logout_sidebar_link");
    private static final By resetAppStateSidebarLink = By.id("reset_sidebar_link");
    private static final By shoppingCartLink = By.className("shopping_cart_link");

    public CheckoutPagetwo(WebDriver driver) {
        this.driver = driver;
        driver.findElement(shoppingCartLink).click();
        driver.findElement(checkoutBtn).click();
        driver.findElement(firstName).sendKeys("Leonardo");
        driver.findElement(lastName).sendKeys("DiCaprio");
        driver.findElement(postalCode).sendKeys("XYZ");
        driver.findElement(continueBtn).click();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public HomePage goToCancel(WebDriver driver) {
        driver.findElement(cancelButton).click();
        return new HomePage(driver);
    }

    public Checkout goToFinish(WebDriver driver) {
        driver.findElement(finishButton).click();
        return new Checkout(driver);
    }

    public CartPage goToCheckoutPageFromCartIcon() {
        driver.findElement(shoppingCartLink).click();
        return new CartPage(driver);
    }

    public List<WebElement> listOfItems() {
        return driver.findElements(cartItem);
    }

    // Helper: open sidebar and wait until links are visible
    private void openSidebar() {
        driver.findElement(burgerMenuBtn).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(allItemsSidebarLink));
    }

    public HomePage goToHomePageFromAllItemsSidebarLink() {
        openSidebar();
        WebElement link = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(allItemsSidebarLink));
        link.click();
        return new HomePage(driver);
    }

    public String getURLFromAboutSidebarLink() {
        openSidebar();
        WebElement aboutLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(aboutSidebarLink));
        aboutLink.click();
        return driver.getCurrentUrl();
    }

    public LoginPage goToLoginPageFromSidebar() {
        openSidebar();
        WebElement logoutLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logoutSidebarLink));
        logoutLink.click();
        return new LoginPage(driver);
    }

    public String getUrlFromLogoutSidebarLink() {
        openSidebar();
        WebElement logoutLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logoutSidebarLink));
        logoutLink.click();
        return driver.getCurrentUrl();
    }

    public String getURLFromAllItemsSidebarLink() {
        openSidebar();
        WebElement allItemsLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(allItemsSidebarLink));
        allItemsLink.click();
        return driver.getCurrentUrl();
    }

    public String getUrlFromResetAppStateSidebarLink() {
        openSidebar();
        WebElement resetLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(resetAppStateSidebarLink));
        resetLink.click();
        return driver.getCurrentUrl();
    }
}
