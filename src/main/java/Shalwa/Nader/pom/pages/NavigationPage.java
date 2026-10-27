package Shalwa.Nader.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class NavigationPage {
    private static WebDriver driver;

    // Social links
    private static final By twitterSocial = By.className("social_twitter");
    private static final By facebookSocial = By.className("social_facebook");
    private static final By linkedinSocial = By.className("social_linkedin");

    // Sidebar
    private static final By burgerMenuBtn = By.id("react-burger-menu-btn");
    private static final By allItemsSidebarLink = By.id("inventory_sidebar_link");
    private static final By aboutSidebarLink = By.id("about_sidebar_link");
    private static final By logoutSidebarLink = By.id("logout_sidebar_link");
    private static final By resetSideBarLink = By.id("reset_sidebar_link");

    // Cart
    private static final By shoppingCartLink = By.className("shopping_cart_link");
    private static final By shoppingCartBadge = By.className("shopping_cart_badge");

    // Footer
    private static final By footerImage = By.className("footer_robot");

    public void setDriver(WebDriver driver) {
        NavigationPage.driver = driver;
    }

    // Helper: wait wrapper (longer timeout for stability)
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Helper: switch to new tab dynamically
    private void switchToOpenedTab(By locator) {
        String originalTab = driver.getWindowHandle();
        int currentWindows = driver.getWindowHandles().size();

        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
        getWait().until(ExpectedConditions.numberOfWindowsToBe(currentWindows + 1));

        for (String tab : driver.getWindowHandles()) {
            if (!originalTab.equals(tab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    // Social links
    public String getUrlFromTwitterLink() {
        switchToOpenedTab(twitterSocial);
        return driver.getCurrentUrl();
    }

    public String getUrlFromFacebookLink() {
        switchToOpenedTab(facebookSocial);
        return driver.getCurrentUrl();
    }

    public String getUrlFromLinkedinLink() {
        switchToOpenedTab(linkedinSocial);
        return driver.getCurrentUrl();
    }

    public String getWindowHandleOfOpenedTwitterTab() {
        switchToOpenedTab(twitterSocial);
        return driver.getWindowHandle();
    }

    public String getWindowHandleOfOpenedFacebookTab() {
        switchToOpenedTab(facebookSocial);
        return driver.getWindowHandle();
    }

    public String getWindowHandleOfOpenedLinkedinTab() {
        switchToOpenedTab(linkedinSocial);
        return driver.getWindowHandle();
    }

    // Sidebar helpers
    private void openSidebar() {
        getWait().until(ExpectedConditions.elementToBeClickable(burgerMenuBtn)).click();
        // Wait for any sidebar link to be visible
        getWait().until(ExpectedConditions.visibilityOfElementLocated(allItemsSidebarLink));
    }

    public String getURLFromAboutSidebarLink() {
        openSidebar();
        getWait().until(ExpectedConditions.elementToBeClickable(aboutSidebarLink)).click();
        return driver.getCurrentUrl();
    }

    public LoginPage goToLoginPageFromSidebar() {
        openSidebar();
        getWait().until(ExpectedConditions.elementToBeClickable(logoutSidebarLink)).click();
        return new LoginPage(driver);
    }

    public String getUrlFromLogoutSidebarLink() {
        goToLoginPageFromSidebar();
        return driver.getCurrentUrl();
    }

    public HomePage goToHomePageFromAllItemsSidebarLink() {
        openSidebar();
        getWait().until(ExpectedConditions.elementToBeClickable(allItemsSidebarLink)).click();
        return new HomePage(driver);
    }

    public String getURLFromAllItemsSidebarLink() {
        goToHomePageFromAllItemsSidebarLink();
        return driver.getCurrentUrl();
    }

    public CartPage goToCheckoutPageFromCartIcon() {
        getWait().until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
        return new CartPage(driver);
    }

    public NavigationPage goToResetAppStateSideBarLink() {
        openSidebar();
        getWait().until(ExpectedConditions.elementToBeClickable(resetSideBarLink)).click();
        return this;
    }

    public String getUrlFromResetAppStateSidebarLink() {
        goToResetAppStateSideBarLink();
        return driver.getCurrentUrl();
    }

    public String getUrlFromCartLink() {
        goToCheckoutPageFromCartIcon();
        return driver.getCurrentUrl();
    }

    // For tests expecting NoSuchElementException, don’t wrap with wait
    public String getBadgeFromShoppingCartIcon() {
        return driver.findElement(shoppingCartBadge).getText();
    }

    public String getSourceOfFooterImage() {
        WebElement footerImg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(footerImage));
        return footerImg.getAttribute("src");
    }
}
