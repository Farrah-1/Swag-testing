package com.sparta.fw;


import com.sparta.fw.pages.SwagCartPage;
import com.sparta.fw.pages.SwagHomePage;
import com.sparta.fw.pages.SwagLoginPage;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddiTests {
    public static WebDriver driver;
    public static SwagCartPage cart;

    public static SwagLoginPage login;

    private String usernameStandard = "standard_user";
    private String userNameLocked = "locked_out_user";
    private String usernamePerformance = "performance_glitch_user";


    //happy paths

    //this should be in a...before each? before all?
    private void extracted() {
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
            extracted();
            Assertions.assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Check that continue shopping links to home page ")
        public void checkThatContinueShoppingLinksToHomePage (){
            extracted();
            cart.goToHomePage();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }


        @Test
        @DisplayName("Check that checkout links to checkout")
        public void checkThat (){
            extracted();
            cart.goToCheckoutPage();
            Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
        }
    }







    @DisplayName("Testing generic tools for framework")
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

        @Disabled
        @Test
        @DisplayName("Check that your cart is displayed")
        public void checkThatYourCartDisplayed (){
            extracted();
            //  cart.textDisplayYourCart();
            //  System.out.println(cart.textDisplayYourCart());
            //   Assertions.assertTrue(cart.textDisplayYourCart());
        }


        @Disabled("don't know what to test for")
        @Test
        @DisplayName("Check that reset app state...")
        public void checkThatResetAppState (){
            extracted();
            cart.goToResetAppState();
            //  Assertions.assertEquals( ,driver.getCurrentUrl());

        }

        @Test
        @DisplayName("Check that about page links to about page")
        public void checkThatAboutPageLinksToAboutPage (){
            extracted();
            cart.goToAboutPage();
            Assertions.assertEquals("https://saucelabs.com/" ,driver.getCurrentUrl());
        }


    }





    @DisplayName("Items in cart")
    @Nested
    class name{


    /*
    @Test
    @DisplayName("Check that checkAllItemsCorrectlyAddedToCart returns the ")
    public void checkThat (){
    }



    @Test
@DisplayName("Check that checkCorrectQuantityOfAllItemsAddedToCart ...")
public void checkThatCheckCorrectQuantityOfAllItemsAddedToCart (){
}

    */


        @Test
        @DisplayName("Check that cartItemAdded returns the items added")
        public void checkThatCartItemAddedReturnsItemsAdded (){
            //stuck - need to do from home page?? Same for the two below

        }

        @Test
        @DisplayName("Check that remove ID returns correct remove ID")
        public void checkThatRemoveIDCorrect (){
            extracted();
            cart.getRemoveId("Bolt T-shirt");
            System.out.println(cart.getRemoveId("Bolt T-shirt"));
        }

    @Test
    @DisplayName("Check that removeItemsFromCart removes items")
    public void checkThatRemoveItemRemovesItems (){
        extracted();
        Assertions.assertTrue(cart.checkItemsRemovedFromCart());
    }

    }









}
