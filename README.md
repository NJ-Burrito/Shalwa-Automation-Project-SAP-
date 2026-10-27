# Shalwa Automation Project (SAP): Selenium Java JUnit 5 test automation 🦜

**Java + Selenium WebDriver test automation for the [SauceDemo](https://www.saucedemo.com/) shop, built on the Page Object Model.**
A portfolio example of UI test automation and end-to-end testing: login with several users, cart, sorting, sidebar and social links, and the full checkout, in 7 JUnit 5 test classes.

By [Nader Alzghoul](https://nj-burrito.github.io/), QA Automation Engineer in Amman, Jordan.

<p>
  <img alt="Java 8" src="https://img.shields.io/badge/Java-8-16171B?style=for-the-badge">
  <img alt="Selenium 4.38" src="https://img.shields.io/badge/Selenium-4.38-3D7F7A?style=for-the-badge">
  <img alt="JUnit 5" src="https://img.shields.io/badge/JUnit-5-34473B?style=for-the-badge">
  <img alt="Maven" src="https://img.shields.io/badge/Build-Maven-A3573B?style=for-the-badge">
  <a href="https://nj-burrito.github.io/"><img alt="Portfolio" src="https://img.shields.io/badge/Portfolio-nj--burrito.github.io-5A1E26?style=for-the-badge"></a>
</p>

## At a glance

| | |
|---|---|
| **Site under test** | SauceDemo (saucedemo.com) |
| **Pattern** | Page Object Model: 6 page objects plus a shared checkout base |
| **Tests** | 7 JUnit 5 classes: login, home, cart, navigation, checkout step one, step two, complete |
| **Browsers** | Chrome, Firefox, Safari, and Chrome emulating an iPhone 11 (`DriversOptions`) |
| **Stack** | Java 8, Selenium WebDriver 4.38, JUnit 5 (Jupiter), Maven |

## What it covers

- **Login:** every SauceDemo user account in one test (standard, locked out, problem, performance glitch, error, visual).
- **Home:** sort by price, ascending and descending (checked by `HomeUt`).
- **Cart:** item names and counts, remove buttons, items stay after Continue shopping, Checkout goes to the right page.
- **Navigation:** sidebar links (All items, About, Logout, Reset app state) and the Facebook, Twitter and LinkedIn links, each opening a new tab.
- **Checkout:** step one (URL, Cancel), step two (item count, Cancel, Finish) and the completion page.

## Get the code

The source is in **`Shalwa_Automation_Project_SAP.rar`** in this repo.

1. Clone the repo and extract the archive (7-Zip or WinRAR).
2. Open the `Shalwa_Automation_Project_SAP` folder in IntelliJ IDEA or Eclipse as a Maven project.
3. You need Java (JDK 8 or newer), Maven, and a browser driver that matches your browser (for example ChromeDriver).

```bash
git clone https://github.com/NJ-Burrito/Shalwa-Automation-Project-SAP-.git
```

## Run it

1. Point Selenium at your driver, in code:
   ```java
   POMUt.setDriverLocation("src/main/resources/chromedriver.exe");
   ```
2. Build: `mvn clean compile`
3. Run a test class from the IDE, for example `CartPageTests` in `src/main/java/Shalwa/Nader/pom/tests/`.

Example, in code:

```java
WebDriver driver = Drivers.getDriver(DriversOptions.CHROME);
LoginPage login = new LoginPage(driver);
HomePage home = login.goToHomePage();
home.addBackpackToCart();
CartPage cart = home.gotoCartPage(driver);
```

## Project structure

```
src/main/java/Shalwa/Nader/pom/
├── driver/     Drivers, DriversOptions (browser creation and options)
├── pages/      LoginPage, HomePage, CartPage, NavigationPage
│   ├── checkoutpage/  Checkout, CheckoutPageone, CheckoutPagetwo
│   └── utility/       HomeUt (sorting and price checks), POMUt (driver path)
└── tests/      LoginPageTests, HomePageTests, CartPageTests, NavigationPageTests,
                CheckoutStepOnePageTests, CheckoutStepTwoPageTests, CheckoutCompletePageTests
```

The repo root also holds the project write-up as a PDF ([Shalwa Automation Project (SAP).pdf](<Shalwa Automation Project (SAP).pdf>)) and a spreadsheet.

## Who made it

Nader Alzghoul (نادر الزغول), QA Automation Engineer in Amman, Jordan. He also builds free, open-source tools:

- [QA-Wrap](https://nj-burrito.github.io/QA-Wrap/): a browser extension that records a test session and writes the bug report.
- [NJ-Kit](https://nj-burrito.github.io/NJ-Kit/): a working method for Claude Code, 40 skills in the kit.
- [Profile-Wrap](https://nj-burrito.github.io/Profile-Wrap/): plans, writes and checks LinkedIn posts.

Portfolio: [nj-burrito.github.io](https://nj-burrito.github.io/) · LinkedIn: [linkedin.com/in/nader-alzghoul](https://www.linkedin.com/in/nader-alzghoul)

## License

Shared under the MIT License.

## Closing note 🦜

This framework was built with curiosity, a little caffeine, and the vocal encouragement of a parrot named **Shalwa**. If you add features or fixes, give Shalwa a shout; she'll squawk her approval. Happy testing.
