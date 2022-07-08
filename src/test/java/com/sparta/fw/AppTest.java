package com.sparta.fw;

import com.sparta.fw.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest
{
    private static WebDriver driver;
    private static SwagLoginPage loginPage;
    private static SwagHomePage homePage;

    private static SwagCheckoutPage checkoutPage;
    private SwagCheckoutOverviewPage checkoutOverviewPage;
    private SwagLoginPage swagLoginPage;
    @BeforeAll
    static void setupAll(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }

    @BeforeEach
    void setup(){
        driver = DriverFactory.getDriver(DriverType.CHROME);
        loginPage = new SwagLoginPage(driver);
    }
    @Nested
    @DisplayName("CheckoutPage")
    class checkoutPageTests {
        @Test
        @DisplayName("check first name can be entered for checkout")
        void checkFirstNameCanBeEnteredForCheckout() {
            Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkFirstNameCanBeEntered());
        }

        @Test
        @DisplayName("check last name can be entered for checkout")
        void checkLastNameCanBeEnteredForCheckout() {
            Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkLastNameCanBeEntered());
        }

        @Test
        @DisplayName("check postcode can be entered for checkout")
        void checkPostCodeCanBeEnteredForCheckout() {
            Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkPostCodeCanBeEntered());
        }

        @Test
        @DisplayName("check empty form cannot be submitted for checkout")
        void checkEmptyFormCannotBeSubmittedForCheckout() {
            Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkEmptyFormCannotBeSubmitted());
        }

        @Test
        @DisplayName("check filled form can be submitted for checkout")
        void checkFilledFormCanBeSubmittedForCheckout() {
            Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkFilledFormCanBeSubmitted());
        }
    }
    @Nested
    @DisplayName("Tests for Homepage")
    class testsForHomePage {

        @BeforeEach
        @DisplayName("Login to homepage")
        void setUp() {
            homePage = loginPage.standardUser();
        }
        @Test
        @DisplayName("Check All the Image Matches Item Title")
        void checkAllTheImagesMatchItemTitles() {
            Assertions.assertTrue(homePage.checkAllImagesMatchItemTitles());
        }

        @Test
        @DisplayName("Check All the Image Matches Item Title")
        void checkItemImageMatchesItemTitle() {
            WebElement item = homePage.getItemElement("Backpack");
            Assertions.assertTrue(homePage.checkImageMatchesItemTitle(item));
        }


        @Test
        @DisplayName("Check all Item Titles Links To Item Page")
        void checkAllItemTitlesLinkToCorrectPages() {
            Assertions.assertTrue(homePage.checkAllItemTitlesLinkToCorrectItemPage());
        }

        @Test
        @DisplayName("Check Item Title Links To Item Page")
        void checkItemTitleLinkToCorrectPages() {
            WebElement item = homePage.getItemElement("Onesie");
            Assertions.assertTrue(homePage.checkItemTitleLinksToCorrectPage(item));
        }

//        @Test
//        @DisplayName("Check If we can remove Products in basket from Homepage ")
//        void checkIfWeCanRemoveProductsInBasketFromHomePage() {
//            Assertions.assertTrue(homePage.);
//        }
//
        @Test
        @DisplayName("Check if all products have the correct price")
        void checkAllProductsHaveTheCorrectPrice() {
            Assertions.assertTrue(homePage.checkAllPricesForItemsAreCorrect());
        }

        @Test
        @DisplayName("Check If the description Matches The Products")
        void checkIfTheDescriptionMatchesTheProduct() {
            Assertions.assertTrue(homePage.productdescription);
        }

        @Test
        @DisplayName("Check If the Home Page Link always takes us to Homepage")
        void checkIfTheHomepageLinkAlwaysTakesUsToHomepage() {
            Assertions.assertTrue(homePage.goToHomePage);
        }

        @Test
        @DisplayName("Check to see if the products get sorted A-Z")
        void checkToSeeIfTheProductGetsSortedAtoZ() {
            Assertions.assertTrue(homePage."a-z");
        }

        @Test
        @DisplayName("Check to see if the products get sorted Z-A")
        void checkToSeeIfTheProductGetsSortedZtoA() {
            Assertions.assertTrue(homePage."z-a");
        }

        @Test
        @DisplayName("Check to see if the products get sorted Low To high")
        void checkToSeeIfTheProductGetsSortedLowToHigh() {
            Assertions.assertTrue(homePage."lowToHigh");
        }

        @Test
        @DisplayName("Check to see if the products get sorted High to Low")
        void checkToSeeIfTheProductGetsSortedHighToLow() {
            Assertions.assertTrue(homePage."z-a");
        }

        @Test
        @DisplayName("Check to see if the reset button works")
        void checkToSeeIfTheResetButtonWorks() {
            Assertions.assertTrue(homePage.goToResetAppState(homePage));
        }
    }


    @Nested
    @DisplayName("Tests For CheckoutOverviewPage")
    class testsForCheckoutOverviewPage {

        @Test
        @DisplayName("Check if the total is to two Decimal places")
        void checkIfTheTotalIsTotwoDecimalPlaces(){
            Assertions.assertTrue(checkoutOverviewPage.);
        }
        @Test
        @DisplayName("Check if we can change anything on the overview Page ")
        void checkIfWeCanChangeAnythingOnTheOverviewPage(){
            Assertions.assertTrue(checkoutOverviewPage.);
        }
        @Test
        @DisplayName("Check to see if the total is correct")
        void checkToSeeIfTheTotalIsCorrect(){
            Assertions.assertTrue(checkoutOverviewPage.);
        }
        @Test
        @DisplayName("Check to see if everything is correct according to selection")
        void checkToSeeIfEverythingIsCorrectAccordingToSelection(){
            Assertions.assertTrue(checkoutOverviewPage.);
        }

    }

    @Nested
    @DisplayName("Tests For login Page")
    class testForLoginPage{
        @Test
        @DisplayName("Check to see if we can login")
        void checkToSeeIfWeCanLogin(){
            Assertions.assertTrue(swagLoginPage.);
        }
        @Test
        @DisplayName("Check for Login TimeOut")
        void checkForLoginTimeOut(){
            Assertions.assertTrue(homePage.goToLoginPage());
        }
    }
    @Nested
    @DisplayName("Tests for sidemenu")
    class testsForSideMenu{
        @Test
        @DisplayName("Does the side menu open")
        void checktoSeeIfTheSideMenuOpens(){
            Assertions.assertTrue(homePage.);
        }
        @Test
        @DisplayName("Check to see if sidemenu open links")
        void checkToSeeIfSideMenuOpensUpALink(){
            Assertions.assertTrue(homePage);
        }
        @Test
        @DisplayName("Check to see if we can open social Media links")
        void checkToSeeIfWeCanOpenSocialMediaLinks(){
            Assertions.assertTrue(homePage.goToFacebookPage()||homePage.goToLinkedinPage()||homePage.goToTwitterPage());
        }
        @Test
        @DisplayName("Check to see if we can click on the terms and condition")
        void checkTpSeeIfWeCanClickOnTheTermsAndCondition(){
            Assertions.assertTrue(homePage.);
        }

    }



    @AfterEach
    void tearDown(){
        driver.close();
    }

    @AfterAll
    static void tearDownAll(){
        driver.quit();
    }
}