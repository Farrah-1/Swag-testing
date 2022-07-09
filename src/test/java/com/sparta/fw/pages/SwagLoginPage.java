package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class SwagLoginPage {

    static WebDriver driver;
    static ChromeDriverService service;

    private By homePageLink = new By.ById("login-button");

    public SwagLoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.saucedemo.com/");
    }



//valid usernames and password entry below:
public SwagHomePage standardUser() {
driver.findElement(By.id("user-name")).sendKeys("standard_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(homePageLink).click();
return new SwagHomePage(driver);
    }
public WebDriver lockedOutUser () {
driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
driver.findElement(homePageLink).click();
return driver;
    }
public SwagHomePage problemUser () {
driver.findElement(By.id("user-name")).sendKeys("problem_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(homePageLink).click();
    return new SwagHomePage(driver);
    }
    public SwagHomePage performanceGlitchUser () {
driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(homePageLink).click();
        return new SwagHomePage(driver);
    }

}
