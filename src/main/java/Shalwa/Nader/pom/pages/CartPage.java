package Shalwa.Nader.pom.pages;

import Shalwa.Nader.pom.pages.checkoutpage.CheckoutPageone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends NavigationPage {

    private final WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    // Cart page locators
    private static final By continueShoppingBtn = By.id("continue-shopping");
    private static final By checkoutBtn = By.id("checkout");
    private static final By cartItem = By.className("cart_item");
    private static final By inventoryItemName = By.className("inventory_item_name");
    private static final By removeButton = By.cssSelector(".btn.btn_secondary.btn_small.cart_button");

    // Item remove buttons
    private static final By removeBackpack = By.id("remove-sauce-labs-backpack");
    private static final By removeBikeLight = By.id("remove-sauce-labs-bike-light");
    private static final By removeBoltTShirt = By.id("remove-sauce-labs-bolt-t-shirt");
    private static final By removeFleeceJacket = By.id("remove-sauce-labs-fleece-jacket");
    private static final By removeOnesie = By.id("remove-sauce-labs-onesie");
    private static final By removeRedTShirt = By.id("remove-test.allthethings()-t-shirt-(red)");

    // Sidebar locators
    private static final By burgerMenuBtn = By.id("react-burger-menu-btn");
    private static final By allItemsSidebarLink = By.id("inventory_sidebar_link");
    private static final By aboutSidebarLink = By.id("about_sidebar_link");
    private static final By logoutSidebarLink = By.id("logout_sidebar_link");
    private static final By resetAppStateSidebarLink = By.id("reset_sidebar_link");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
    }

    public void goToHomePageFromLogin() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.goToHomePage();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public HomePage clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
        return new HomePage(driver);
    }

    public CheckoutPageone clickCheckout(WebDriver driver) {
        driver.findElement(checkoutBtn).click();
        return new CheckoutPageone(driver);
    }

    public List<WebElement> listOfItems() {
        return driver.findElements(cartItem);
    }

    public void removeBackpack() {
        driver.findElement(removeBackpack).click();
    }

    public void removeAllItems() {
        driver.findElement(removeBackpack).click();
        driver.findElement(removeBikeLight).click();
        driver.findElement(removeBoltTShirt).click();
        driver.findElement(removeFleeceJacket).click();
        driver.findElement(removeOnesie).click();
        driver.findElement(removeRedTShirt).click();
    }

    public List<WebElement> listOfRemoveItemBtn() {
        return driver.findElements(removeButton);
    }

    public List<String> listOfItemNames() {
        List<WebElement> items = listOfItems();
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.findElement(inventoryItemName).getText());
        }
        return itemNames;
    }

    public boolean isItemsPersistent() {
        addAllItemsAndGoToCartPage();
        int expected = listOfItems().size();
        homePage = clickContinueShopping();
        homePage.gotoCartPage(driver);
        int actual = listOfItems().size();
        return actual == expected;
    }

    public boolean isReturnItemListCorrect() {
        addAllItemsAndGoToCartPage();
        String actual = listOfItemNames().toString();
        String expected = "[Sauce Labs Backpack, Sauce Labs Bike Light, Sauce Labs Bolt T-Shirt, Sauce Labs Fleece Jacket, Sauce Labs Onesie, Test.allTheThings() T-Shirt (Red)]";
        return actual.equals(expected);
    }

    public int numberOfSomeItemsInCart() {
        goToHomePageFromLogin();
        homePage.addBikeLightToCart();
        homePage.addBoltTShirtToCart();
        homePage.gotoCartPage(driver);
        return listOfItems().size();
    }

    public int numberOfAllItemsInCart() {
        addAllItemsAndGoToCartPage();
        return listOfItems().size();
    }

    private void addAllItemsAndGoToCartPage() {
        goToHomePageFromLogin();
        homePage.addAllItemsToCart();
        homePage.gotoCartPage(driver);
    }

    public int removeAllItemsButton() {
        addAllItemsAndGoToCartPage();
        removeAllItems();
        return listOfItems().size();
    }

    public int removeOneItemButton() {
        addAllItemsAndGoToCartPage();
        removeBackpack();
        return listOfItems().size();
    }

    public boolean numberOfRemoveButtons() {
        addAllItemsAndGoToCartPage();
        return listOfRemoveItemBtn().size() == listOfItems().size();
    }

    // Sidebar helpers
    private void openSidebar() {
        driver.findElement(burgerMenuBtn).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(allItemsSidebarLink));
    }

    public String getUrlFromResetAppStateSidebarLink() {
        openSidebar();
        WebElement resetLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(resetAppStateSidebarLink));
        resetLink.click();
        return driver.getCurrentUrl();
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

    public String getURLFromAboutSidebarLink() {
        openSidebar();
        WebElement aboutLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(aboutSidebarLink));
        aboutLink.click();
        return driver.getCurrentUrl();
    }
}
