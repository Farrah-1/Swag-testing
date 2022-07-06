package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwagCheckoutOverviewPage {

    private WebDriver driver;

    private By finish = new By.ById("finish");
    private By cancel = new By.ById("cancel");

    public SwagCheckoutOverviewPage(WebDriver driver){
        this.driver = driver;

    }
    public String getURL() {
        return driver.getCurrentUrl();
    }

    public WebDriver goToFinish(){
        driver.findElement(finish).click();
        return driver;
    }

    public WebDriver goToCancel(){
        driver.findElement(cancel).click();
        return driver;
    }


}
