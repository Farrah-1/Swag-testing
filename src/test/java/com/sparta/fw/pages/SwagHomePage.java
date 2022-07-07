package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
    public WebDriver goToLinkedinPage() {
        driver.findElement(linkedinLink).click();
        return driver;
    }
    public List<WebElement> getAllItems() {
        return driver.findElements(By.className("inventory_item"));
    }

    public WebElement getItemElement(String item) {
        WebElement returnedElement = null;
        for (WebElement itemElement : getAllItems()) {
            String itemTitle = itemElement.findElement(By.className("inventory_item_name")).getText();
            if (itemTitle.equalsIgnoreCase(item)){
                returnedElement = itemElement;
            };
        }
        return returnedElement;
    }

    public boolean checkAllImagesMatchItemTitles() {
        ArrayList<WebElement> itemImages = new ArrayList<>();

        for (WebElement item : getAllItems()) {
            itemImages.add(item.findElement(By.className("inventory_item_img")));
        }

        boolean matches = false;

        for (int i = 0; i < getAllItems().size(); i++) {
            String itemImageDescription = itemImages.get(i).findElement(By.className("inventory_item_img")).getAttribute("alt");
            String itemTitle = getAllItems().get(i).findElement(By.className("inventory_item_name")).getText();
            matches = itemTitle.equals(itemImageDescription);
        }
        return matches;
    }

    public boolean checkAllPricesForItemsAreCorrect() {
        boolean matches = false;
        for (WebElement item : getAllItems()) {
            String itemTitle = item.findElement(By.className("inventory_item_name")).getText();
            String itemPrice = item.findElement(By.className("inventory_item_price")).getText();

            matches = comparePriceToItemTitle(itemTitle, itemPrice);
        }
        return matches;
    }

    private static boolean comparePriceToItemTitle(String itemTitle, String itemPrice) {
        return switch (itemTitle) {
            case "Sauce Labs Backpack" -> itemPrice.equals("$29.99");
            case "Sauce Labs Bike Light" -> itemPrice.equals("$9.99");
            case "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)" -> itemPrice.equals("$15.99");
            case "Sauce Labs Fleece Jacket" -> itemPrice.equals("$49.99");
            case "Sauce Labs Onesie" -> itemPrice.equals("$7.99");
            default -> false;
        };
    }

    public boolean checkAllItemTitlesLinkToItemPage() {
        boolean matches = false;
        for (WebElement item : getAllItems()) {
            WebElement itemElement = item.findElement(By.className("inventory_item_name"));
            String itemTitle = item.getText();
            itemElement.click();
            String itemPageUrl = driver.getCurrentUrl();

            matches = compareUrlToItemTitle(itemTitle, itemPageUrl);
        }
        return matches;
    }

    private static boolean compareUrlToItemTitle(String itemTitle, String itemPageUrl) {
        return switch (itemTitle) {
            case "Sauce Labs Backpack" -> itemPageUrl.equals("https://www.saucedemo.com/inventory-item.html?id=4");
            case "Sauce Labs Bike Light" -> itemPageUrl.equals("https://www.saucedemo.com/inventory-item.html?id=0");
            case "Sauce Labs Bolt T-Shirt"-> itemPageUrl.equals("https://www.saucedemo.com/inventory-item.html?id=1");
            case "Sauce Labs Fleece Jacket" -> itemPageUrl.equals("https://www.saucedemo.com/inventory-item.html?id=5");
            case "Sauce Labs Onesie" -> itemPageUrl.equals("https://www.saucedemo.com/inventory-item.html?id=2");
            case "Test.allTheThings() T-Shirt (Red)" -> itemPageUrl.equals("https://www.saucedemo.com/inventory-item.html?id=3");
            default -> false;
        };
    }

    public boolean checkAddToCartAddsItemCorrectly() {
        boolean addsCorrectly = false;
        for (WebElement item : getAllItems()) {
            item.findElement(By.className("btn_inventory")).click();
        }
        goToCartPage();
        return addsCorrectly;
        
    }
}
