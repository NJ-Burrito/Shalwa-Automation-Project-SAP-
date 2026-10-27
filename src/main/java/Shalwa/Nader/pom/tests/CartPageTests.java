package Shalwa.Nader.pom.tests;

import Shalwa.Nader.pom.pages.utility.POMUt;
import Shalwa.Nader.pom.driver.Drivers;
import Shalwa.Nader.pom.driver.DriversOptions;
import Shalwa.Nader.pom.pages.CartPage;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class CartPageTests {
    static WebDriver driver;
    private static final String DRIVER_LOCATION = "src/main/resources/chromedriver.exe";
    private CartPage cartPage;

    @BeforeAll
    static void setupAll() {
        POMUt.setDriverLocation(DRIVER_LOCATION);
        driver = Drivers.getDriver(DriversOptions.CHROME);
        if (driver == null) {
            throw new IllegalStateException("Driver creation failed. Check Drivers.getDriver implementation.");
        }
        try {
            driver.manage().window().maximize();
        } catch (Exception ignored) {}
    }

    @BeforeEach
    void setup() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.goToHomePage();
        cartPage = homePage.gotoCartPage(driver);

        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(8))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/cart.html"));
        } catch (Exception ignored) {}
    }

    @Test
    @DisplayName("Check continue shopping button returns to HomePage")
    void checkContinueShoppingButtonReturnsToHomePage() {
        System.out.println("[INFO] Test returning to shopping... (1/9)");
        cartPage.clickContinueShopping();
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/inventory.html"));
        } catch (Exception e) {
            Assertions.fail("Inventory page did not load within timeout after clicking Continue Shopping");
        }
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        System.out.println("[PASS] Test returning to shopping... (1/9)");
    }

    @Test
    @DisplayName("Check that when continue shopping and going back to cart items persists")
    void checkThatWhenContinueShoppingAndGoingBackToCartItemsPersists() {
        System.out.println("[INFO] Testing cart persistence... (2/9)");
        Assertions.assertTrue(cartPage.isItemsPersistent(), "Cart items should persist after navigating away and back");
        System.out.println("[PASS] Cart persistence verified... (2/9)");
    }

    @Test
    @DisplayName("Check that return list item names are correct")
    void checkThatReturnListItemNamesAreCorrect() {
        System.out.println("[INFO] Testing item list names... (3/9)");
        Assertions.assertTrue(cartPage.isReturnItemListCorrect(), "Returned item list names should match expected values");
        System.out.println("[PASS] Item list names verified... (3/9)");
    }

    @Test
    @DisplayName("Check that correct amount of items in cart")
    void checkThatCorrectAmountOfItemsInCart() {
        System.out.println("[INFO] Testing partial cart item count... (4/9)");
        Assertions.assertEquals(2, cartPage.numberOfSomeItemsInCart(), "Expected 2 items in the partial-cart scenario");
        System.out.println("[PASS] Partial cart item count verified... (4/9)");
    }

    @Test
    @DisplayName("Check that when all items added is correct amount")
    void checkThatWhenAllItemsAddedIsCorrectAmount() {
        System.out.println("[INFO] Testing full cart item count... (5/9)");
        Assertions.assertEquals(6, cartPage.numberOfAllItemsInCart(), "Expected 6 items when all are added");
        System.out.println("[PASS] Full cart item count verified... (5/9)");
    }

    @Test
    @DisplayName("Check all remove buttons work")
    void checkAllRemoveButtonsWork() {
        System.out.println("[INFO] Testing remove all items... (6/9)");
        Assertions.assertEquals(0, cartPage.removeAllItemsButton(), "All items should be removed and count should be 0");
        System.out.println("[PASS] Remove all items verified... (6/9)");
    }

    @Test
    @DisplayName("Check that the remove buttons work")
    void checkThatTheRemoveButtonsWork() {
        System.out.println("[INFO] Testing remove one item... (7/9)");
        Assertions.assertEquals(5, cartPage.removeOneItemButton(), "After removing one item expected remaining count is 5");
        System.out.println("[PASS] Remove one item verified... (7/9)");
    }

    @Test
    @DisplayName("Check list of remove buttons are correct numbers")
    void checkListOfRemoveButtonsAreCorrectNumbers() {
        System.out.println("[INFO] Testing remove buttons count... (8/9)");
        Assertions.assertTrue(cartPage.numberOfRemoveButtons(), "Remove buttons count should be correct");
        System.out.println("[PASS] Remove buttons count verified... (8/9)");
    }

    @Test
    @DisplayName("Check Checkout button goes to correct url")
    void checkCheckoutButtonGoesToCorrectUrl() {
        System.out.println("[INFO] Testing checkout navigation... (9/9)");
        cartPage.clickCheckout(driver);
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/checkout-step-one.html"));
        } catch (Exception e) {
            Assertions.fail("Checkout page did not load within timeout after clicking Checkout");
        }
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
        System.out.println("[PASS] Checkout navigation verified... (9/9)");
    }

    @AfterAll
    static void tearDownAll() {
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {} finally { driver = null; }
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if (driver != null) {
                try { driver.manage().deleteAllCookies(); } catch (Exception ignored) {}
                try {
                    ((org.openqa.selenium.JavascriptExecutor) driver)
                            .executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
                } catch (Exception ignored) {}
            }
        } catch (Exception ignored) {}
    }
}
