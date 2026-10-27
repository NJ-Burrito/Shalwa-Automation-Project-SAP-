package Shalwa.Nader.pom.tests;

import Shalwa.Nader.pom.pages.utility.POMUt;
import Shalwa.Nader.pom.driver.Drivers;
import Shalwa.Nader.pom.driver.DriversOptions;
import Shalwa.Nader.pom.pages.checkoutpage.CheckoutPageone;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePageTests {
    static WebDriver driver;
    private CheckoutPageone checkoutStepOnePage;
    private static final String DRIVER_LOCATION = "src/main/resources/chromedriver.exe";

    @BeforeAll
    static void setupAll() {
        POMUt.setDriverLocation(DRIVER_LOCATION);
        driver = Drivers.getDriver(DriversOptions.CHROME);
        if (driver == null) {
            throw new IllegalStateException("Driver creation failed.");
        }
        try {
            driver.manage().window().maximize();
        } catch (Exception ignored) {}
    }

    @BeforeEach
    void setup() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.goToHomePage();
        checkoutStepOnePage = new CheckoutPageone(driver);
    }

    @Test
    @DisplayName("Check that the URL is correct")
    void checkThatTheUrlIsCorrect(){
        System.out.println("[INFO] Testing checkout step one URL... (1/4)");
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html", checkoutStepOnePage.getURL());
        System.out.println("[PASS] Checkout step one URL verified... (1/4)");
    }

    @Test
    @DisplayName("Check that clicking the cancel button sends you to the cart page")
    void checkThatClickingTheCancelButtonSendsYouToTheCartPage(){
        System.out.println("[INFO] Testing cancel button navigation... (2/4)");
        Assertions.assertEquals("https://www.saucedemo.com/cart.html", checkoutStepOnePage.cancelCheckout(driver).getUrl());
        System.out.println("[PASS] Cancel button navigation verified... (2/4)");
    }

    @Test
    @DisplayName("Check that clicking the continue button sends you to checkout page 2")
    void checkThatClickingTheContinueButtonSendsYouToCheckoutPage2(){
        System.out.println("[INFO] Testing continue button navigation... (3/4)");
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html", checkoutStepOnePage.goToCheckoutPage2().getUrl());
        System.out.println("[PASS] Continue button navigation verified... (3/4)");
    }

    @Test
    @DisplayName("Check that you go to checkout page 2 by entering strings into the form and pressing continue")
    void checkThatYouGoToCheckoutPage2ByEnteringStringsIntoTheFormAndPressingContinue(){
        System.out.println("[INFO] Testing form entry + continue navigation... (4/4)");
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html", checkoutStepOnePage.goToCheckoutPage2().getUrl());
        System.out.println("[PASS] Form entry + continue navigation verified... (4/4)");
    }

    @AfterAll
    static void tearDownAll() {
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
        }
    }
}
