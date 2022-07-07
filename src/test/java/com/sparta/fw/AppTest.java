package com.sparta.fw;

import com.sparta.fw.pages.SwagLoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest 
{
    private static WebDriver driver;
    private static SwagLoginPage loginPage;
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

    @AfterEach
    void tearDown(){
        driver.close();
    }

    @AfterAll
    static void tearDownAll(){
        driver.quit();
    }
}
