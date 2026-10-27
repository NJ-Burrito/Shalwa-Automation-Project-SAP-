package Shalwa.Nader.pom.tests;

import Shalwa.Nader.pom.pages.utility.POMUt;
import Shalwa.Nader.pom.driver.Drivers;
import Shalwa.Nader.pom.driver.DriversOptions;
import Shalwa.Nader.pom.pages.CartPage;
import Shalwa.Nader.pom.pages.checkoutpage.Checkout;
import Shalwa.Nader.pom.pages.checkoutpage.CheckoutPageone;
import Shalwa.Nader.pom.pages.checkoutpage.CheckoutPagetwo;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutStepTwoPageTests {

    static WebDriver driver;
    private HomePage homePage;
    private CheckoutPagetwo checkoutStepTwoPage;
    private CheckoutPageone checkoutStepOnePage;
    private static final String DRIVER_LOCATION = "src/main/resources/chromedriver.exe";

    @BeforeAll
    static void setupAll() {
        POMUt.setDriverLocation(DRIVER_LOCATION);
        driver = Drivers.getDriver(DriversOptions.CHROME);
        if (driver == null) throw new IllegalStateException("Driver creation failed.");
        try { driver.manage().window().maximize(); } catch (Exception ignored) {}
    }

    @BeforeEach
    void setup() {
        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.goToHomePage();
        checkoutStepTwoPage = new CheckoutPagetwo(driver);
    }

    @Test
    @DisplayName("Check that checkoutPage2 link is correct")
    void checkThatCheckoutPage2LinkIsCorrect() {
        System.out.println("[INFO] Testing checkout step two URL... (1/10)");
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", checkoutStepTwoPage.getUrl());
        System.out.println("[PASS] Checkout step two URL verified... (1/10)");
    }

    @Test
    @DisplayName("Check cancel button returns to HomePage")
    void checkCancelButtonReturnsToHomePage() {
        System.out.println("[INFO] Testing cancel button navigation... (2/10)");
        homePage = checkoutStepTwoPage.goToCancel(driver);
        assertEquals("https://www.saucedemo.com/inventory.html", homePage.getUrl());
        System.out.println("[PASS] Cancel button navigation verified... (2/10)");
    }

    @Test
    @DisplayName("Check finish shopping button goes to CheckoutComplete page")
    void checkFinishButtonReturnsToCheckoutCompletePage() {
        System.out.println("[INFO] Testing finish button navigation... (3/10)");
        Checkout checkoutCompletePage = checkoutStepTwoPage.goToFinish(driver);
        assertEquals("https://www.saucedemo.com/checkout-complete.html", checkoutCompletePage.getUrl());
        System.out.println("[PASS] Finish button navigation verified... (3/10)");
    }

    @Test
    @DisplayName("Check that correct amount of items in cart")
    void checkThatCorrectAmountOfItemsInCart() {
        System.out.println("[INFO] Testing cart item count... (4/10)");
        CartPage cartPage = homePage.gotoCartPage(driver);
        Assertions.assertEquals(0, checkoutStepTwoPage.listOfItems().size());
        System.out.println("[PASS] Cart item count verified... (4/10)");
    }

    @Test
    @DisplayName("Check that clicking the cart icon takes you to the Cart page")
    void checkThatClickingTheCartIconTakesYouToTheCartPage() {
        System.out.println("[INFO] Testing cart icon navigation... (5/10)");
        Assertions.assertEquals("https://www.saucedemo.com/cart.html", checkoutStepTwoPage.getUrlFromCartLink());
        System.out.println("[PASS] Cart icon navigation verified... (5/10)");
    }

    @Nested
    class SidebarTests {

        @Test
        @DisplayName("Check that the all items sidebar goes to homepage")
        void checkThatTheAllItemsSidebarGoesToHomepage() {
            System.out.println("[INFO] Testing sidebar 'All Items' link... (6/10)");
            assertEquals("https://www.saucedemo.com/inventory.html", checkoutStepTwoPage.getURLFromAllItemsSidebarLink());
            System.out.println("[PASS] Sidebar 'All Items' link verified... (6/10)");
        }

        @Test
        @DisplayName("Check that the about sidebar link works")
        void checkThatTheAboutSidebarLinkWorks() {
            System.out.println("[INFO] Testing sidebar 'About' link... (7/10)");
            assertEquals("https://saucelabs.com/", checkoutStepTwoPage.getURLFromAboutSidebarLink());
            System.out.println("[PASS] Sidebar 'About' link verified... (7/10)");
        }

        @Test
        @DisplayName("Check that the logout sidebar link takes you to the login page")
        void checkThatTheLogoutSidebarLinkTakeYouToTheLoginPage() {
            System.out.println("[INFO] Testing sidebar 'Logout' link... (8/10)");
            assertEquals("https://www.saucedemo.com/", checkoutStepTwoPage.getUrlFromLogoutSidebarLink());
            System.out.println("[PASS] Sidebar 'Logout' link verified... (8/10)");
        }

        @Test
        @DisplayName("Check that the reset app state sidebar link from homepage gives correct url")
        void checkThatTheResetAppStateSidebarLinkGiveTheSameUrl() {
            System.out.println("[INFO] Testing sidebar 'Reset App State' link from homepage... (9/10)");
            assertEquals("https://www.saucedemo.com/checkout-step-two.html", checkoutStepTwoPage.getUrlFromResetAppStateSidebarLink());
            System.out.println("[PASS] Sidebar 'Reset App State' link from homepage verified... (9/10)");
        }

        @Test
        @DisplayName("Check that the reset app state sidebar link from the cart page stays on the same url")
        void checkThatTheResetAppStateSidebarLinkFromTheCartPageStaysOnTheSameUrl() {
            System.out.println("[INFO] Testing sidebar 'Reset App State' link from cart page... (10/10)");
            assertEquals("https://www.saucedemo.com/cart.html",
                    checkoutStepTwoPage.goToCheckoutPageFromCartIcon().getUrlFromResetAppStateSidebarLink());
            System.out.println("[PASS] Sidebar 'Reset App State' link from cart page verified... (10/10)");
        }
    }

    @AfterAll
    static void tearDownAll() {
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
        }
    }
}
