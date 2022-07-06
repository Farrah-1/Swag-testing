package com.sparta.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwagCheckoutPage {

    private final WebDriver driver;
    private final By cancelButtonLink = new By.ById("cancel");
    private final By continueButtonLink = new By.ById("continue");
    private final By cartButtonLink = new By.ById("shopping_cart_container");
    private final By firstNameFieldLink = new By.ById("first-name");
    private final By lastNameFieldLink = new By.ById("last-name");
    private final By postCodeFieldLink = new By.ById("postal-code");

    public SwagCheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkIfCancelButtonGoesBackToCart(){
        driver.findElement(cancelButtonLink).click();
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html");
    }

    public boolean checkIfCartButtonGoesBackToCart(){
        driver.findElement(cartButtonLink).click();
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html");
    }

    public boolean checkFirstNameCanBeEntered(){
        driver.findElement(firstNameFieldLink).sendKeys("Testing");
        return driver.findElement(firstNameFieldLink).getText().contains("Testing");
    }

    public boolean checkLastNameCanBeEntered(){
        driver.findElement(lastNameFieldLink).sendKeys("Testing");
        return driver.findElement(lastNameFieldLink).getText().contains("Testing");
    }

    public boolean checkPostCodeCanBeEntered(){
        driver.findElement(postCodeFieldLink).sendKeys("Testing");
        return driver.findElement(postCodeFieldLink).getText().contains("Testing");
    }

    public boolean checkEmptyFormCannotBeSubmitted(){
        String firstName = String.valueOf(driver.findElement(firstNameFieldLink).getClass());
        String lastName = String.valueOf(driver.findElement(lastNameFieldLink).getClass());
        String postCode = String.valueOf(driver.findElement(postCodeFieldLink).getClass());
        driver.findElement(continueButtonLink).click();
        String errorClassName = "input_error form_input error";

        return firstName.equals(errorClassName) || lastName.equals(errorClassName) || postCode.equals(errorClassName);
    }

    public boolean checkFilledFormCanBeSubmitted(){
        driver.findElement(firstNameFieldLink).sendKeys("Testing");
        driver.findElement(lastNameFieldLink).sendKeys("Testing");
        driver.findElement(postCodeFieldLink).sendKeys("Testing");
        driver.findElement(By.id("continue")).click();

        return driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html");
    }
}
