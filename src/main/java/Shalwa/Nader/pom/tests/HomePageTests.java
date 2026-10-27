package Shalwa.Nader.pom.tests;

import Shalwa.Nader.pom.pages.utility.POMUt;
import Shalwa.Nader.pom.driver.Drivers;
import Shalwa.Nader.pom.driver.DriversOptions;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class HomePageTests {
    private static WebDriver driver;
    private static HomePage homePage;
    private static final String DRIVER_LOCATION = "src/main/resources/chromedriver.exe";

    @BeforeAll
    static void setupAll() {
        POMUt.setDriverLocation(DRIVER_LOCATION);
        driver = Drivers.getDriver(DriversOptions.CHROME);
        if (driver == null) {
            throw new IllegalStateException("Driver creation failed.");
        }
        try { driver.manage().window().maximize(); } catch (Exception ignored) {}
    }

    @BeforeEach
    void setup() {
        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.goToHomePage();
    }

    @Test
    @DisplayName("Products are sorted price ascending when sort by is price ascending")
    void productsAreSortedPriceAscendingWhenSortByIsPriceAscending() {
        System.out.println("[INFO] Testing sort by price ascending... (1/2)");
        Assertions.assertTrue(homePage.sortProductsByPriceAscending().isPriceAscending(),
                "Products should be sorted ascending by price");
        System.out.println("[PASS] Sort by price ascending verified... (1/2)");
    }

    @Test
    @DisplayName("Products are sorted price descending when sort by is price descending")
    void productsAreSortedPriceDescendingWhenSortByIsPriceDescending() {
        System.out.println("[INFO] Testing sort by price descending... (2/2)");
        Assertions.assertTrue(homePage.sortProductsByPriceDescending().isPriceDescending(),
                "Products should be sorted descending by price");
        System.out.println("[PASS] Sort by price descending verified... (2/2)");
    }

    @AfterAll
    static void tearDownAll() {
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
        }
    }
}
