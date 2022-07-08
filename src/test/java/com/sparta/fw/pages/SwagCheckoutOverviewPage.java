package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwagCheckoutOverviewPage {

    private WebDriver driver;

    private By finish = new By.ById("finish");
    private By cancel = new By.ById("cancel");
    private By quantity = new By.ByClassName("cart_quantity");
    private By total = new By.ByClassName("summary_total_label");
    private By tax = new By.ByClassName("summary_tax_label");
    private By totalBeforeTax = new By.ByClassName("summary_subtotal_label");
    private By burgerMenuLink= new By.ById("react-burger-menu-btn");
    private By cartLink= new By.ByClassName("shopping_cart_link");
    private By twitterLink= new By.ByClassName("social_twitter");
    private By facebookLink= new By.ByClassName("social_facebook");
    private By linkedinLink= new By.ByClassName("social_linkedin");

    private By policyTerms = new By.ByXPath("//*[@id=\"page_wrapper\"]/footer/div/text()[3]");

    public SwagCheckoutOverviewPage(WebDriver driver){
        this.driver = driver;

    }
    public String getURL() {
        return driver.getCurrentUrl();
    }
    public SwagCheckoutCompletePage goToCheckoutFinishPage(){
        driver.findElement(finish).click();
        return new SwagCheckoutCompletePage(driver);
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

    public WebDriver goToTwitterPage(){
        driver.findElement(twitterLink).click();
        return driver;
    }
    public WebDriver goToFacebookPage(){
        driver.findElement(facebookLink).click();
        return driver;
    }
    public WebDriver goToLinkedinPage() {
        driver.findElement(linkedinLink).click();
        return driver;
    }

    public WebDriver goToPoliciesTermsOfService(){
        driver.findElement(policyTerms).click();
        return driver;
    }

    public SwagCartPage goToCartPage(){
        driver.findElement(cartLink).click();
        return new SwagCartPage(driver);
    }
    public boolean goToFinish(){
        driver.findElement(finish).click();
        return getURL().equals("https://www.saucedemo.com/checkout-complete.html");
    }

    public boolean goToCancel(){
        driver.findElement(cancel).click();
        return getURL().equals("https://www.saucedemo.com/inventory.html");
    }

    public WebDriver changeQuantityOnOverviewPage(){
        driver.findElement(quantity).sendKeys("2");
        return driver;
    }

//    public boolean taxCalculation(){
//        double taxPercentage = 1.08;
//        driver.findElement(tax).getText().equals(String.valueOf(totalBeforeTax * taxPercentage));
//
//    }

    /*
    public boolean isTotalInTwoDecPlaces(){
        String str = driver.findElement(total).toString();
        int dec = str.substring(str.indexOf(".")).length() -1;
        return;

    }

     */

}