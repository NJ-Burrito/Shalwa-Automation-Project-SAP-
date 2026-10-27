package Shalwa.Nader.pom.tests;

import Shalwa.Nader.pom.pages.utility.POMUt;
import Shalwa.Nader.pom.driver.Drivers;
import Shalwa.Nader.pom.driver.DriversOptions;
import Shalwa.Nader.pom.pages.HomePage;
import Shalwa.Nader.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTests {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static final String DRIVER_LOCATION = "src/main/resources/chromedriver.exe";

    // All known usernames
    private static final String[] USERS = {
            "standard_user",
            "problem_user",
            "performance_glitch_user",
            "error_user",
            "visual_user",
            "locked_out_user",
            "incorrect_user" // invalid case
    };

    @BeforeAll
    static void setupAll() {
        POMUt.setDriverLocation(DRIVER_LOCATION);
        driver = Drivers.getDriver(DriversOptions.CHROME);
        if (driver == null) {
            throw new IllegalStateException("Driver creation failed.");
        }
        try { driver.manage().window().maximize(); } catch (Exception ignored) {}
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        System.out.println("[INFO] LoginPageTests initialized. Browser ready.");
    }

    @BeforeEach
    void beforeEach() {
        driver.get("https://www.saucedemo.com/");
        clearLoginFields();
    }

    @Test
    @DisplayName("Try all users login")
    void tryAllUsersLogin() {
        int count = 1;
        for (String user : USERS) {
            System.out.println("[INFO] Testing login for user: " + user + " ... (" + count + "/" + USERS.length + ")");
            driver.get("https://www.saucedemo.com/");
            clearLoginFields();

            if (user.equals("locked_out_user")) {
                String error = loginPage.lockedOutLogin();
                Assertions.assertTrue(error.toLowerCase().contains("locked out"),
                        "Locked out user should show error message");
                System.out.println("[PASS] Locked out user error verified... (" + count + "/" + USERS.length + ")");
            } else if (user.equals("incorrect_user")) {
                String errorAttr = loginPage.invalidLogin();
                Assertions.assertNotNull(errorAttr, "Invalid user should trigger error attribute");
                System.out.println("[PASS] Invalid user error verified... (" + count + "/" + USERS.length + ")");
            } else {
                HomePage home = loginPage.loginToPage(user);
                assertEquals("https://www.saucedemo.com/inventory.html", home.getUrl(),
                        user + " should reach inventory page");
                System.out.println("[PASS] " + user + " login verified... (" + count + "/" + USERS.length + ")");
            }
            count++;
        }
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("[INFO] Tearing down LoginPageTests...");
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
        }
        System.out.println("[PASS] Browser closed. Test suite completed.");
    }

    // Utility: clear username/password fields safely to reuse the same page and window
    private void clearLoginFields() {
        try {
            driver.findElement(By.id("user-name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            driver.findElement(By.id("password")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        } catch (Exception e) {
            try {
                driver.findElement(By.id("user-name")).clear();
                driver.findElement(By.id("password")).clear();
            } catch (Exception ignored) {}
        }
    }
}
