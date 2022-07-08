package com.sparta.fw;

import com.sparta.fw.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
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
        @DisplayName("Check an item Image Matches Item Title")
        void checkItemImageMatchesItemTitle() {
            WebElement item = homePage.getItemElement("Backpack");
            Assertions.assertTrue(homePage.checkImageMatchesItemTitle(item));
        }
        @Test
        @DisplayName("Check correct number appears in cart badge for items added")
        void checkCorrectNumberInCartBadge() {
            homePage.addAllItemsToCart();
            Assertions.assertTrue(homePage.checkCorrectCartNumberBadge(homePage.getAllItems().size()));
        }
        @Test
        @DisplayName("Check that multiple of the same item can be added to the cart")
        void checkMultipleOfSameItemCanBeAdded() {
            WebElement jacket = homePage.getItemElement("Jacket");
            homePage.addItemToCart(jacket, 3);
            Assertions.assertTrue(homePage.checkCorrectCartNumberBadge(3));
        }
        //        @Test
//        @DisplayName("Check all Item Titles Link To Item Page")
//        void checkAllItemTitlesLinkToCorrectPages() {
//            Assertions.assertTrue(homePage.checkAllItemTitlesLinkToCorrectItemPage());
//        }
        @Test
        @DisplayName("Check an Item Title Links To Item Page")
        void checkItemTitleLinkToCorrectPages() {
            WebElement item = homePage.getItemElement("Onesie");
            System.out.println(item.findElement(By.className("inventory_item_name")).getText());
            Assertions.assertTrue(homePage.checkItemTitleLinksToCorrectPage(item));
        }
        //        @Test
//        @DisplayName("Check all item images link to item page")
//        void checkAllItemImageLinkToItemPage() {
//            Assertions.assertTrue(homePage.checkAllItemImagesLinkToCorrectItemPage());
//        }
        @Test
        @DisplayName("Check an item image links to item page")
        void checkAnItemImageLinksToItemPage() {
            WebElement item = homePage.getItemElement("Jacket");
            Assertions.assertTrue(homePage.checkItemImageLinksToCorrectPage(item));
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
        @DisplayName("Check if a product has the correct price")
        void checkProductHasTheCorrectPrice() {
            WebElement item = homePage.getItemElement("Light");
            Assertions.assertTrue(homePage.checkPriceForItemIsCorrect(item));
        }
        @Test
        @DisplayName("Check to see if A-Z filter orders items correctly")
        void checkToSeeIfTheProductGetsSortedAtoZ() {
            Assertions.assertTrue(homePage.checkFilterCorrectlyFiltersItemsAtoZ());
        }
        @Test
        @DisplayName("Check to see if Z-A filter orders items correctly")
        void checkToSeeIfTheProductGetsSortedZtoA() {
            Assertions.assertTrue(homePage.checkFilterCorrectlyFiltersItemsZtoA());
        }
        @Test
        @DisplayName("Check to see if Low To high filer orders items correctly")
        void checkToSeeIfTheProductGetsSortedLowToHigh() {
            Assertions.assertTrue(homePage.checkFilterCorrectlyFiltersItemsLowToHigh());
        }
        @Test
        @DisplayName("Check to see if High To Low filer orders items correctly")
        void checkToSeeIfTheProductGetsSortedHighToLow() {
            Assertions.assertTrue(homePage.checkFilterCorrectlyFiltersItemsHighToLow());
        }
        @Test
        @DisplayName("Check to see if the reset button resets the whole page")
        void checkToSeeIfTheResetButtonResetsThePage() {
            homePage.addAllItemsToCart();
            Assertions.assertTrue(homePage.checkResetAppStateResetsApp());
        }
        @Test
        @DisplayName("Check to see if the twitter icon links to the correct page")
        void checkToSeeIfTheTwitterIconLinksToPage() {
            Assertions.assertTrue(homePage.checkTwitterLinksToCorrectPage());
        }
        @Test
        @DisplayName("Check to see if the facebook icon links to the correct page")
        void checkToSeeIfTheFacebookIconLinksToPage() {
            Assertions.assertTrue(homePage.checkFacebookLinksToCorrectPage());
        }
        @Test
        @DisplayName("Check to see if the LinkedIn icon links to the correct page")
        void checkToSeeIfTheLinkedInIconLinksToPage() {
            Assertions.assertTrue(homePage.checkLinkedInLinksToCorrectPage());
        }
        @Test
        @DisplayName("Check to see if About links to the correct page")
        void checkToSeeIfTheAboutLinksToPage() {
            Assertions.assertTrue(homePage.checkAboutLinksToCorrectPage());
        }
        @Test
        @DisplayName("Check to see if Log Out links to the correct page")
        void checkToSeeIfTheLogOutLinksToPage() {
            Assertions.assertTrue(homePage.checkLogoutLinksToCorrectPage());
        }
        @Test
        @DisplayName("Check to see if cart icon links to the correct page")
        void checkToSeeIfTheCartIconLinksToPage() {
            Assertions.assertTrue(homePage.checkCartLinksToCorrectPage());
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
        @DisplayName("Check to see if we can login as Standard User")
        void checkToSeeIfWeCanLoginAsStandardUser(){
            loginPage.standardUser();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }
        @Test
        @DisplayName("Check to see if we can login as LockedOut User")
        void checkToSeeIfWeCanLoginAsLockedOutUser(){
            loginPage.lockedOutUser();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }
        @Test
        @DisplayName("Check to see if we can login as Problem User")
        void checkToSeeIfWeCanLoginAsProblemUser(){
            loginPage.problemUser();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }
        @Test
        @DisplayName("Check to see if we can login as Performance User")
        void checkToSeeIfWeCanLoginAsPerformanceUser(){
            loginPage.performanceGlitchUser();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
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
        @DisplayName("Check to see if we can open Twitter link")
        void checkToSeeIfWeCanOpenTwitterLink(){
            homePage.goToTwitterPage();
            Assertions.assertEquals("https://twitter.com/saucelabs",driver.getCurrentUrl());
        }
        @Test
        @DisplayName("Check to see if we can open Facebook link")
        void checkToSeeIfWeCanOpenFacebookLink(){
            homePage.goToFacebookPage();
            Assertions.assertEquals("https://www.facebook.com/saucelabs",driver.getCurrentUrl());
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