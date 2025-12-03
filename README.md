# Shalwa Automation Project SAP 🦜✨

## Project Description
**Shalwa Automation Project SAP** is a Java + Selenium test automation framework that exercises the GUI of the SauceDemo site. It automates common user flows such as logging in with different users, adding and removing items from the cart, verifying cart persistence, sorting products, navigating the sidebar and social links, and completing checkout flows. The framework follows the **Page Object Model** for clarity, reusability, and maintainability.

## Key Features
- **End-to-end GUI tests** for SauceDemo flows ✅  
- **Page Object Model** structure for clean separation of concerns ✅  
- **Cross-browser ready** drivers abstraction for Chrome, Firefox, Safari ✅  
- **Utility helpers** for common checks like price sorting ✅  
- **JUnit test suite** with modular test classes for easy execution ✅

## Tech Stack
- **Language**: Java  
- **Test framework**: JUnit  
- **Browser automation**: Selenium WebDriver  
- **Build tool**: Maven  
- **Design pattern**: Page Object Model

## Prerequisites 🚀
- **Java JDK 11 or newer** installed and **JAVA_HOME** set  
- **Apache Maven** installed and on your PATH  
- **Browser driver** that matches your browser version (for example ChromeDriver)  
- Recommended IDE: **IntelliJ IDEA** or **Eclipse**

## Installation Guide
### 1. Clone the repository
```bash
git clone https://github.com/NJ-Burrito/Shalwa-Automation-Project-SAP-.git
cd Shalwa_Automation_Project_SAP
```

### 2. Set the browser driver path
Open the project and set the driver path in code or via system property. Example using the helper utility:
```java
POMUt.setDriverLocation("src/main/resources/chromedriver.exe");
```
Alternatively set the system property when running Maven:
```bash
mvn -Dwebdriver.chrome.driver=/path/to/chromedriver test
```

### 3. Build the project
```bash
mvn clean compile
```

### 4. Run the test suite
Run all tests:
```bash
mvn test
```
Run a single test class:
```bash
mvn -Dtest=LoginPageTests test
```
You can also run tests directly from your IDE.

## Configuration Tips
- Keep the driver binary version aligned with your browser version.  
- Use explicit waits in tests to improve stability.  
- If you need mobile viewport simulation, use the CHROME_IPHONE11 option and set the window size in the driver setup.

## Usage Examples 🧪
### Programmatic example
```java
// Create a browser instance
WebDriver driver = Drivers.getDriver(DriversOptions.CHROME);

// Use page objects
LoginPage login = new LoginPage(driver);
HomePage home = login.goToHomePage();
CartPage cart = home.gotoCartPage(driver);

// Perform actions
home.addBackpackToCart();
home.addBikeLightToCart();
cart = home.gotoCartPage(driver);
```

### Running a focused test from Maven
```bash
mvn -Dtest=CartPageTests test
```

### Common helper usage
```java
// Set driver path at runtime
POMUt.setDriverLocation("src/main/resources/chromedriver.exe");

// Check price sorting
HomePage home = new HomePage(driver);
home.sortProductsByPriceAscending();
boolean sorted = home.isPriceAscending();
```

## Project Structure Overview
- **driver**: `Drivers`, `DriversOptions` — centralizes browser creation and options  
- **pages**: Page objects such as `LoginPage`, `HomePage`, `CartPage`, `CheckoutPageone`, `CheckoutPagetwo`, `NavigationPage`  
- **pages/utility**: helpers like `HomeUt` and `POMUt`  
- **tests**: JUnit test classes (for example `CartPageTests`)  
- **resources**: place browser drivers and test data here

## Best Practices and Tips
- Keep tests **idempotent** and **isolated** so they can run in any order.  
- Prefer **explicit waits** over sleeps for stability.  
- Use small, focused test methods that assert one behavior each.  
- Add logging and screenshots on failure to speed up debugging.

## Contributing 🤝
Contributions are welcome and encouraged. To contribute:
1. Fork the repository.  
2. Create a feature branch with a descriptive name.  
3. Add tests for any new behavior.  
4. Open a pull request with a clear description and screenshots or logs if relevant.

**Suggested improvements**
- Add cross-browser CI using GitHub Actions.  
- Add reporting with Allure or Surefire HTML reports.  
- Replace manual waits with robust explicit wait utilities.  
- Add Page Factory or component-level abstractions for complex pages.

## License 📜
This project is shared under the **MIT License**. You are free to use, modify, and distribute the code. Please include attribution and the LICENSE file in your copy of the repository. Add a `LICENSE` file to the repo with the full MIT text.

## Quick Reference Commands
```bash
# Build
mvn clean compile

# Run all tests
mvn test

# Run a single test class
mvn -Dtest=LoginPageTests test

# Run with a specific chromedriver path
mvn -Dwebdriver.chrome.driver=/path/to/chromedriver test
```

## Project Description for GitHub
**Shalwa Automation Project SAP**  
A beginner-friendly Java + Selenium Page Object Model test framework for the SauceDemo site. SAP automates login, product sorting, cart operations, sidebar navigation, social links, and checkout flows. Designed for clarity and maintainability, the framework includes reusable page objects, driver abstractions, utility helpers, and a JUnit test suite.

## Closing Note 🦜💬
Thanks for exploring **Shalwa Automation Project SAP**. This framework was built with curiosity, a little caffeine, and the vocal encouragement of a parrot named **Shalwa**. If you add features or fixes, give Shalwa a shout — she’ll squawk her approval. Happy testing and welcome to the flock!
