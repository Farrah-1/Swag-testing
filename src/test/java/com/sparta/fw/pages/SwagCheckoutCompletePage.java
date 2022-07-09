package com.sparta.fw.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwagCheckoutCompletePage {

    private final WebDriver driver;
    private final By burgerMenuLink= new By.ById("react-burger-menu-btn");
    private final By homeButtonLink = new By.ById("back-to-products");
    private final By cartButtonLink = new By.ById("shopping_cart_container");

    public SwagCheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public SwagCartPage goToCartPage(){
        driver.findElement(cartButtonLink).click();
        return new SwagCartPage(driver);
    }

    public SwagHomePage goToHomePage(){
        driver.findElement(homeButtonLink).click();
        return new SwagHomePage(driver);
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

    public boolean checkHomeButtonGoesToHomePage(){
        driver.findElement(homeButtonLink).click();
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public boolean checkIfCartButtonGoesBackToCart(){
        driver.findElement(cartButtonLink).click();
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html");
    }

    public boolean checkCartIsEmptyAfterCheckout(){
        driver.findElement(cartButtonLink).click();
        return goToCartPage().checkCartIsEmpty();
    }
}
