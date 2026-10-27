package Shalwa.Nader.pom.tests;

import Shalwa.Nader.pom.pages.utility.POMUt;
import Shalwa.Nader.pom.driver.Drivers;
import Shalwa.Nader.pom.driver.DriversOptions;
import Shalwa.Nader.pom.pages.checkoutpage.Checkout;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutCompletePageTests {
    static WebDriver driver;
    private Checkout checkoutCompletePage;

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
        checkoutCompletePage = new Checkout(driver);
    }

    @Test
    @DisplayName("Check that checkoutCompletePage link is correct")
    void checkThatCheckoutCompletePageLinkIsCorrect() {
        System.out.println("[INFO] Testing checkout complete page link... (1/1)");
        assertEquals("https://www.saucedemo.com/checkout-complete.html", checkoutCompletePage.getUrl());
        System.out.println("[PASS] Checkout complete page link verified... (1/1)");
    }

    @AfterAll
    static void tearDownAll() {
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
        }
    }
}
