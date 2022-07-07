package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwagCheckoutOverviewPage {

    private WebDriver driver;

    private By finish = new By.ById("finish");
    private By cancel = new By.ById("cancel");
    private By quantity = new By.ByClassName("cart_quantity");
    private By total = new By.ByClassName("summary_total_label");
    private By tax = new By.ByClassName("summary_tax_label");
    private By totalBeforeTax = new By.ByClassName("summary_subtotal_label");

    public SwagCheckoutOverviewPage(WebDriver driver){
        this.driver = driver;

    }
    public String getURL() {
        return driver.getCurrentUrl();
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

    public boolean taxCalculation(){
        double taxPercentage = 1.08;
        driver.findElement(tax).equals(totalBeforeTax * taxPercentage);

    }




}