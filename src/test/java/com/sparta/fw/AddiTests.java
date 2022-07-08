package com.sparta.fw;


import com.sparta.fw.pages.SwagCartPage;
import com.sparta.fw.pages.SwagHomePage;
import com.sparta.fw.pages.SwagLoginPage;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class AddiTests {
    public static WebDriver driver;
    public static SwagCartPage cart;

    public static SwagLoginPage login;
    public static SwagHomePage homePage;

    private void extracted() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        login = new SwagLoginPage(driver);
        cart = new SwagCartPage(driver);
        homePage = cart.goToHomePage();
        driver.getCurrentUrl();
    }

    private void extractedNoHome() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        login = new SwagLoginPage(driver);
        cart = new SwagCartPage(driver);
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









}
