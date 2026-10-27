package Shalwa.Nader.pom.tests;

import Shalwa.Nader.pom.pages.utility.POMUt;
import Shalwa.Nader.pom.driver.Drivers;
import Shalwa.Nader.pom.driver.DriversOptions;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NavigationPageTests {
    private static final String DRIVER_LOCATION = "src/main/resources/chromedriver.exe";
    static WebDriver driver;
    static HomePage homePage;

    @BeforeAll
    static void setupAll() {
        System.out.println("[INFO] Initializing driver...");
        POMUt.setDriverLocation(DRIVER_LOCATION);
        driver = Drivers.getDriver(DriversOptions.CHROME);
        System.out.println("[INFO] Driver ready. Starting test suite.");
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        System.out.println("[START] Running test: " + testInfo.getDisplayName());
        LoginPage loginPage = new LoginPage(driver);
        homePage = loginPage.goToHomePage();
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("[PASS] Completed test: " + testInfo.getDisplayName());
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("[INFO] Closing driver...");
        driver.quit();
        System.out.println("[INFO] Test suite finished.");
    }

    @Nested
    class FooterTests {

        @Test
        @DisplayName("Check that the Facebook link is correct")
        void checkThatTheFacebookLinkIsCorrect() {
            Assertions.assertEquals("https://www.facebook.com/saucelabs", homePage.getUrlFromFacebookLink());
        }

        @Test
        @DisplayName("Check that the twitter link is correct")
        void checkThatTheTwitterLinkIsCorrect() {
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", homePage.getUrlFromTwitterLink());
        }

        @Test
        @DisplayName("Check that the twitter link opens a new tab")
        void checkThatTheTwitterLinkOpensANewTab() {
            Assertions.assertNotEquals(driver.getWindowHandle(), homePage.getWindowHandleOfOpenedTwitterTab());
        }

        @Test
        @DisplayName("Check that the facebook link opens a new tab")
        void checkThatTheFacebookLinkOpensANewTab() {
            Assertions.assertNotEquals(driver.getWindowHandle(), homePage.getWindowHandleOfOpenedFacebookTab());
        }

        @Test
        @DisplayName("Check that the linkedin link opens a new tab")
        void checkThatTheLinkedinLinkOpensANewTab() {
            Assertions.assertNotEquals(driver.getWindowHandle(), homePage.getWindowHandleOfOpenedLinkedinTab());
        }
    }

    @Nested
    class SidebarTests {

        @Test
        @DisplayName("Check that the about sidebar link works")
        void checkThatTheAboutSidebarLinkWorks() {
            Assertions.assertEquals("https://saucelabs.com/", homePage.getURLFromAboutSidebarLink());
        }

        @Test
        @DisplayName("Check that the all items Side bar goes to homepage ")
        void checkThatTheAllItemsSideBarGoesToHomepage() {
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", homePage.getURLFromAllItemsSidebarLink());
        }

        @Test
        @DisplayName("Check that the logout sidebar link take you to the login page")
        void checkThatTheLogoutSidebarLinkTakeYouToTheLoginPage() {
            Assertions.assertEquals("https://www.saucedemo.com/", homePage.getUrlFromLogoutSidebarLink());
        }

        @Test
        @DisplayName("Check that the reset app state sidebar link from homepage give correct url")
        void checkThatTheResetAppStateSidebarLinkGiveTheSameUrl() {
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", homePage.getUrlFromResetAppStateSidebarLink());
        }

        @Test
        @DisplayName("Check that the reset app state sidebar link from the cart page stays on the same url")
        void checkThatTheResetAppStateSidebarLinkFromTheCartPageStaysOnTheSameUrl() {
            Assertions.assertEquals("https://www.saucedemo.com/cart.html", homePage.goToCheckoutPageFromCartIcon().getUrlFromResetAppStateSidebarLink());
        }

        @Test
        @DisplayName("Check that the reset app state removes cart badge")
        void checkThatTheResetAppStateRemovesCartBadge() {
            Assertions.assertThrows(NoSuchElementException.class,
                    () -> homePage.addBikeLightToCart().goToResetAppStateSideBarLink().getBadgeFromShoppingCartIcon());
        }

        @Nested
        class CartIcon {

            @Test
            @DisplayName("Check that clicking the cart icon takes you to the Cart page")
            void checkThatClickingTheCartIconTakesYouToTheCartPage() {
                Assertions.assertEquals("https://www.saucedemo.com/cart.html", homePage.getUrlFromCartLink());
            }
        }
    }
}
