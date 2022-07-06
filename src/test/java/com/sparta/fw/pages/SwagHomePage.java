package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwagHomePage {

    private WebDriver driver;

    private By burgerMenuLink= new By.ById("react-burger-menu-btn");
    private By cartLink= new By.ByClassName("shopping_cart_link");



    private By twitterLink= new By.ByClassName("social_twitter");
    private By facebookLink= new By.ByClassName("social_facebook");
    private By linkedinLink= new By.ByClassName("social_linkedin");


    public SwagHomePage(WebDriver driver){
        this.driver=driver;
    }

    public SwagLoginPage goToLoginPage(){
        driver.findElement(burgerMenuLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        driver.findElement(By.id("logout_sidebar_link")).click();
        return new SwagLoginPage(driver);
    }
    public WebDriver goToAboutPage(){
        driver.findElement(burgerMenuLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));
        driver.findElement(By.id("about_sidebar_link")).click();
        return driver;
    }
    public WebDriver goToResetAppState(){
        driver.findElement(burgerMenuLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        driver.findElement(By.id("reset_sidebar_link")).click();
        return driver;
    }

    public SwagCartPage goToCartPage(){
        driver.findElement(cartLink).click();
        return new SwagCartPage(driver);
    }

    public WebDriver goToTwitterPage(){
        driver.findElement(twitterLink).click();
        return driver;
    }
    public WebDriver goToFacebookPage(){
        driver.findElement(facebookLink).click();
        return driver;
    }
    public WebDriver goToLinkedinPage(){
        driver.findElement(linkedinLink).click();
        return driver;
    }

}
