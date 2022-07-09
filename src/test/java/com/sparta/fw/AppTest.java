package com.sparta.fw;

import com.sparta.fw.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class AppTest
{
    private static WebDriver driver;
    private static SwagLoginPage loginPage;
    private static SwagHomePage homePage;
    private static SwagCartPage cart;

    private static SwagCheckoutPage checkoutPage;
    private SwagCheckoutOverviewPage checkoutOverviewPage;
    private SwagLoginPage swagLoginPage;
    @BeforeAll
    static void setupAll(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
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





    private void extracted() {
        //  System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        //   driver = new ChromeDriver();
        loginPage = new SwagLoginPage(driver);
        cart = loginPage.standardUser().goToCartPage();
        //    homePage = cart.goToHomePage();
        driver.getCurrentUrl();
    }

    private void extractedNoHome() {
        //   System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        //   driver = new ChromeDriver();
        loginPage = new SwagLoginPage(driver);
        cart = loginPage.standardUser().goToCartPage();
        driver.getCurrentUrl();
    }

    @DisplayName("Check links to other pages")
    @Nested
    class CheckLinksToOtherPages{
        @Test
        @DisplayName("Check that cart happy path works")
        public void checkThatCartOpens (){
            extractedNoHome();
            Assertions.assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Check that continue shopping links to home page ")
        public void checkThatContinueShoppingLinksToHomePage (){
            extractedNoHome();
            cart.goToHomePage();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }


        @Test
        @DisplayName("Check that checkout links to checkout")
        public void checkThat (){
            extractedNoHome();
            cart.goToCheckoutPage();
            Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
        }

        @Disabled("Method not working")
        @Test
        @DisplayName("Check that checkAllItemTitlesLinkToItemPage returns boolean")
        public void checkThatcheckAllItemTitlesLinkToItemPageIsBool (){
            extractedNoHome();
            cart.checkAllItemTitlesLinkToItemPage();
            Assertions.assertTrue(cart.checkAllItemTitlesLinkToItemPage());
        }

        @Test
        @DisplayName("Check that items compareUrlToItemTitle compares with correct page")
        public void checkThatcompareUrlToItemTitleComparesCorrectPage (){
            String[] actualItemNames = {"Sauce Labs Backpack","Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)" };
            String[] actualItemURL = {"https://www.saucedemo.com/inventory-item.html?id=4","https://www.saucedemo.com/inventory-item.html?id=0", "https://www.saucedemo.com/inventory-item.html?id=1", "https://www.saucedemo.com/inventory-item.html?id=5", "https://www.saucedemo.com/inventory-item.html?id=2", "https://www.saucedemo.com/inventory-item.html?id=3" };
            extracted();
            int sum =0;
            for(int i = 0; i < actualItemNames.length; i++){
                boolean bool = cart.compareUrlToItemTitle(actualItemNames[i], actualItemURL[i]);
                if(!bool){
                    sum ++;
                }
            }
            Assertions.assertEquals(0, sum);
        }

        @Test
        @DisplayName("Check that checkAllItemTitlesLinkToItemPage XYZ")
        public void checkThatCheckAllItemTitlesLinkToItemPageDOEsSOMETHING (){

        }

    }

    @DisplayName("Testing generic tools for framework") //all work
    @Nested
    class TestingGenericTools{
        @Test
        @DisplayName("Check that split at space splits into array")
        public void checkThatSplitAtSpaceSplitsIntoAnArray (){
            //USE MOCKING
            extracted();
            String[] actual = cart.splitAtSpace("hello world");
            String[] expected = {"hello", "world"};
            Assertions.assertArrayEquals(actual, expected);
        }

        @Test
        @DisplayName("Check that split at space lowers characters")
        public void checkThatSplitAtSpaceLowersCharacters (){
            //USE MOCKING
            extracted();
            String[] actual = cart.splitAtSpace("HELLO WORLD");
            String[] expected = {"hello", "world"};
            Assertions.assertArrayEquals(actual, expected);
        }


    }

    @DisplayName("Check burger menu")
    @Nested
    class CheckBurgerMenu{
        @Disabled("don't know what to test for")
        @Test
        @DisplayName("Check that reset app state...")
        public void checkThatResetAppState (){
            extracted();
            cart.goToResetAppState();
            //  Assertions.assertEquals( ,driver.getCurrentUrl());

        }

        @Test
        @DisplayName("Check that logout returns logout")
        public void checkThat (){
            extractedNoHome();
            cart.logout();
            Assertions.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Check that about page links to about page")
        public void checkThatAboutPageLinksToAboutPage (){
            extractedNoHome();
            cart.goToAboutPage();
            Assertions.assertEquals("https://saucelabs.com/" ,driver.getCurrentUrl());
        }


    }

    @DisplayName("Items in cart")
    @Nested
    class name{

        @Disabled("Incomplete")
        @Test
        @DisplayName("Check that cartItemAdded returns the items added")
        public void checkThatCartItemAddedReturnsItemsAdded (){
            extracted();
            List<String> itemNames = new ArrayList<>();
            List<String> expectedItemNames = new ArrayList<>();

            homePage.getAllItems();
            //add all items to cart
            for (WebElement item : homePage.getAllItems()) {
                item.findElement(By.className("btn_inventory")).click();
            }

            cart.cartItemAdded();

            for(WebElement itemInCart : cart.cartItemAdded()){
                itemInCart.getAttribute("id");
                System.out.println(itemInCart.getAttribute("id"));

            }
        }

        @Test
        @DisplayName("Check that remove ID returns correct remove ID")
        public void checkThatRemoveIDCorrect (){
            extracted();
            cart.getRemoveId("Bolt T-shirt");
            System.out.println(cart.getRemoveId("Bolt T-shirt"));
        }


        @Disabled("need add item method?")
        @Test
        @DisplayName("Check that removeItemsFromCart removes items")
        public void checkThatRemoveItemRemovesItems (){
            //test this better

            extracted();
            Assertions.assertTrue(cart.checkItemsRemovedFromCart());
        }

        @Test
        @DisplayName("Check that checkCartIsEmpty returns true if empty")
        public void checkThatCheckCartIsEmptyReturnsTrueIfEmpty (){
            extracted();
            cart.removeItemsFromCart();
            Assertions.assertTrue(cart.checkCartIsEmpty());
        }

        @Test
        @DisplayName("Check that checkCartIsEmpty returns false if not empty")
        public void checkThatCheckCartIsEmptyReturnsFalseIfNotEmpty (){
            extracted();
            //  homePage.addItemToCart();
            //add items to cart from cart thingy
            Assertions.assertFalse(homePage.goToCartPage().checkCartIsEmpty());
        }


        @Test
        @DisplayName("Check that comparePriceToItemTitle correctly compares the price")
        public void checkThatComparePriceToItemTitleCorrectlyComparesPrice (){
            extracted();
            String[] actualItemNames = {"Sauce Labs Backpack","Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie" };
            String[] actualItemPrices = {"$29.99", "$9.99", "$15.99", "$15.99","$49.99", "$7.99"};
            int sum = 0;

            for( int i = 0; i < actualItemNames.length; i++) {
                boolean matches = cart.comparePriceToItemTitle(actualItemNames[i], actualItemPrices[i]);
                if (!matches) {
                    sum += 1;
                }
            }
            Assertions.assertEquals(sum, 0);
        }

        @Test
        @DisplayName("Check that checkAllPricesForItemsAreCorrect correctly compares the price")
        public void checkThatCheckAllPricesForItemsAreCorrectCorrectlyReturnsBoolean (){
            extracted();
            String[] actualItemNames = {"Sauce Labs Backpack","Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie" };
            String[] actualItemPrices = {"$29.99", "$9.99", "$15.99", "$15.99","$49.99", "$7.99"};
            int sum = 0;

            for( int i = 0; i < actualItemNames.length; i++) {
                boolean matches = cart.comparePriceToItemTitle(actualItemNames[i], actualItemPrices[i]);
                if (!matches) {
                    sum += 1;
                }
            }
            Assertions.assertEquals(sum, 0);
        }
    }


        @AfterEach
        void tearDown() {
            driver.close();
        }

        @AfterAll
        static void tearDownAll() {
            driver.quit();
        }
    }
