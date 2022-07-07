package com.sparta.fw;

import com.sparta.fw.pages.SwagHomePage;
import com.sparta.fw.pages.SwagLoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest 
{
    private static WebDriver driver;
    private static SwagLoginPage loginPage;

    private static SwagHomePage homePage;
    @BeforeAll
    static void setupAll(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        loginPage = new SwagLoginPage(driver);
    }

    @Test
    @DisplayName("check first name can be entered for checkout")
    void checkFirstNameCanBeEnteredForCheckout(){
        Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkFirstNameCanBeEntered());
    }

    @Test
    @DisplayName("check last name can be entered for checkout")
    void checkLastNameCanBeEnteredForCheckout(){
        Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkLastNameCanBeEntered());
    }

    @Test
    @DisplayName("check postcode can be entered for checkout")
    void checkPostCodeCanBeEnteredForCheckout(){
        Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkPostCodeCanBeEntered());
    }

    @Test
    @DisplayName("check empty form cannot be submitted for checkout")
    void checkEmptyFormCannotBeSubmittedForCheckout(){
        Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkEmptyFormCannotBeSubmitted());
    }

    @Test
    @DisplayName("check filled form can be submitted for checkout")
    void checkFilledFormCanBeSubmittedForCheckout(){
        Assertions.assertTrue(loginPage.standardUser().goToCartPage().goToCheckoutPage().checkFilledFormCanBeSubmitted());
    }
    @Test
    @DisplayName("Check All the Image Matches Item Title")
    void checkAllTheImagesMatchItemTitles(){
        Assertions.assertTrue(homePage.itemImage);
    }
    @Test
    @DisplayName("Check Item Title Links To Item Page")
    void checkItemTitleLinksToItemOage(){
        Assertions.assertTrue(homePage.itemTitle);
    }

    @Test
    @DisplayName("Check how long it takes to load the page")
    void checkHowLongItTakesToLoadThePage(){
        Assertions.assertTrue(homePage.loadingTime);
    }

    @Test
    @DisplayName("Check If we can remove Products in basket from Homepage ")
    void checkIfWeCanRemoveProductsInBasketFromHomePage(){
        Assertions.assertTrue(homePage.removeProduct);
    }
    @Test
    @DisplayName("Check If the image Matches The Products")
    void checkIfTheImageMatchesTheProduct(){
        Assertions.assertTrue(homePage.imagedescription);
    }@Test
    @DisplayName("Check If the price is right for The Products")
    void checkIfTheIsRightForTheProduct(){
        Assertions.assertTrue(homePage.productPrice);
    }@Test
    @DisplayName("Check If the description Matches The Products")
    void checkIfTheDescriptionMatchesTheProduct(){
        Assertions.assertTrue(homePage.productdescription);
    }
   @Test
   @DisplayName("Check If the Home Page Link always takes us to Homepage")
   void checkIfTheHomepageLinkAlwaysTakesUsToHomepage(){
        Assertions.assertTrue(homePage.goToHomePage);
   }
   @Test
   @DisplayName("Check to see if the products get sorted A-Z")
   void checkToSeeIfTheProductGetsSortedAtoZ(){
        Assertions.assertTrue(homePage."a-z");
    }@Test
        @DisplayName("Check to see if the products get sorted Z-A")
        void checkToSeeIfTheProductGetsSortedZtoA(){
        Assertions.assertTrue(homePage."z-a");
    }@Test
        @DisplayName("Check to see if the products get sorted Low To high")
        void checkToSeeIfTheProductGetsSortedLowToHigh(){
        Assertions.assertTrue(homePage."lowToHigh");
    }@Test
        @DisplayName("Check to see if the products get sorted High to Low")
        void checkToSeeIfTheProductGetsSortedHighToLow(){
        Assertions.assertTrue(homePage."z-a");
}



   @Test
   @DisplayName("Check to see if the reset button works")
   void checkToSeeIfTheResetButtonWorks(){
        Assertions.assertTrue(homePage.goToResetAppState(homePage));
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
