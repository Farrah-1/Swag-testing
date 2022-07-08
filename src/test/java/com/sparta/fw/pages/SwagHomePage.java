package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

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
            if (itemTitle.toLowerCase().contains(item.toLowerCase())){
                returnedElement = itemElement;
            };
        }
        return returnedElement;
    }

    public boolean checkAllImagesMatchItemTitles() {
        boolean matches = false;

        for (WebElement item : getAllItems()) {
            if (checkImageMatchesItemTitle(item)) {
                matches = true;
            } else {
                matches = false;
                break;
            }

        }
        return matches;
    }

    public boolean checkImageMatchesItemTitle(WebElement item) {
        WebElement imageElement = item.findElement(By.className("inventory_item_img"));
        String itemImageDescription = imageElement.findElement(By.className("inventory_item_img")).getAttribute("alt");
        String itemTitle = item.findElement(By.className("inventory_item_name")).getText();

        return itemTitle.equals(itemImageDescription);
    }

    public boolean checkAllPricesForItemsAreCorrect() {
        boolean matches = false;
        for (WebElement item : getAllItems()) {

            if (checkPriceForItemIsCorrect(item)) {
                matches = true;
            } else {
                matches = false;
                break;
            }
        }
        return matches;
    }

    public boolean checkPriceForItemIsCorrect(WebElement item) {
        String itemTitle = item.findElement(By.className("inventory_item_name")).getText();
        String itemPrice = item.findElement(By.className("inventory_item_price")).getText();

        return comparePriceToItemTitle(itemTitle, itemPrice);
    }

    public static boolean comparePriceToItemTitle(String itemTitle, String itemPrice) {
        return switch (itemTitle) {
            case "Sauce Labs Backpack" -> itemPrice.equals("$29.99");
            case "Sauce Labs Bike Light" -> itemPrice.equals("$9.99");
            case "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)" -> itemPrice.equals("$15.99");
            case "Sauce Labs Fleece Jacket" -> itemPrice.equals("$49.99");
            case "Sauce Labs Onesie" -> itemPrice.equals("$7.99");
            default -> false;
        };
    }

    public boolean checkAllItemTitlesLinkToCorrectItemPage() {
        boolean matches = false;
        for (WebElement item : getAllItems()) {

            if (checkItemLinksToCorrectPage(item)) {
                matches = true;
            } else {
                matches = false;
                break;
            }
        }
        return matches;
    }

    public boolean checkItemLinksToCorrectPage(WebElement item) {
        WebElement itemElement = item.findElement(By.className("inventory_item_name"));
        String itemTitle = item.getText();
        itemElement.click();
        String itemPageUrl = driver.getCurrentUrl();

        return compareUrlToItemTitle(itemTitle, itemPageUrl);
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

    public void addAllItemsToCart() {
        for (WebElement item : getAllItems()) {
            item.findElement(By.className("btn_inventory")).click();
        }
    }

    public void addItemToCart(WebElement item) {
        item.findElement(By.className("btn_inventory")).click();
    }

    public boolean checkCorrectCartNumberBadge(int numberItemsAdded) {
        String cartBadgeNumber = driver.findElement(By.className("shopping_cart_badge")).getText();
        return Integer.parseInt(cartBadgeNumber) == numberItemsAdded;
    }

    public boolean checkFilterCorrectlyFiltersItemsZtoA(){
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > div.right_component > span > select > option:nth-child(2)")).click();

        ArrayList<String> itemName = new ArrayList<>();
        for (WebElement item : getAllItems())  itemName.add(item.findElement(By.className("inventory_item_name")).getText());

        ArrayList<String> sortedList = new ArrayList<>(itemName);
        sortedList.sort(Collections.reverseOrder());

        return itemName.equals(sortedList);
    }

    public boolean checkFilterCorrectlyFiltersItemsAtoZ(){
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > div.right_component > span > select > option:nth-child(1)")).click();

        return checkItemsOrderedAtoZ();
    }

    public boolean checkFilterCorrectlyFiltersItemsLowToHigh(){
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > div.right_component > span > select > option:nth-child(3)")).click();

        ArrayList<Double> itemPrices = new ArrayList<>();
        for (WebElement item : getAllItems()) {
            String itemPriceString = item.findElement(By.className("inventory_item_price")).getText().substring(1);
            itemPrices.add(Double.parseDouble(itemPriceString));
        }

        ArrayList<Double> sortedList = new ArrayList<>(itemPrices);
        Collections.sort(sortedList);

        return itemPrices.equals(sortedList);
    }

    public boolean checkFilterCorrectlyFiltersItemsHighToLow(){
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > div.right_component > span > select > option:nth-child(4)")).click();

        ArrayList<Double> itemPrices = new ArrayList<>();
        for (WebElement item : getAllItems()) {
            String itemPriceString = item.findElement(By.className("inventory_item_price")).getText().substring(1);
            itemPrices.add(Double.parseDouble(itemPriceString));
        }

        ArrayList<Double> sortedList = new ArrayList<>(itemPrices);
        sortedList.sort(Collections.reverseOrder());

        return itemPrices.equals(sortedList);
    }

    public boolean checkTwitterLinksToCorrectPage() {
        driver.findElement(By.linkText("Twitter")).click();
        String currentHandle = driver.getWindowHandle();
        Set<String> allHandles =  driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!currentHandle.equals(handle)) driver.switchTo().window(handle);
        }
        return driver.getCurrentUrl().equals("https://twitter.com/saucelabs");
    }

    public boolean checkFacebookLinksToCorrectPage() {
        driver.findElement(By.linkText("Facebook")).click();
        String currentHandle = driver.getWindowHandle();
        Set<String> allHandles =  driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!currentHandle.equals(handle)) driver.switchTo().window(handle);
        }
        return driver.getCurrentUrl().equals("https://www.facebook.com/saucelabs");
    }

    public boolean checkLinkedInLinksToCorrectPage() {
        driver.findElement(By.linkText("LinkedIn")).click();
        String currentHandle = driver.getWindowHandle();
        Set<String> allHandles =  driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!currentHandle.equals(handle)) driver.switchTo().window(handle);
        }
        return driver.getCurrentUrl().equals("https://www.linkedin.com/company/sauce-labs/");
    }

    public boolean checkAboutLinksToCorrectPage() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));
        driver.findElement(By.id("about_sidebar_link")).click();

        return driver.getCurrentUrl().equals("https://saucelabs.com/");
    }

    public boolean checkLogoutLinksToCorrectPage() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        driver.findElement(By.id("logout_sidebar_link")).click();

        return driver.getCurrentUrl().equals("https://www.saucedemo.com/");
    }

    public boolean checkCartLinksToCorrectPage() {
        driver.findElement(By.className("shopping_cart_link")).click();
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html");
    }

    public boolean checkResetAppStateResetsApp() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        driver.findElement(By.id("reset_sidebar_link")).click();

        boolean isAllReset = false;
        for (WebElement item : getAllItems()) {
            if (checkRemoveButtonChangesToAdd(item) && checkCorrectCartNumberBadge(0) && checkItemsOrderedAtoZ()){
                isAllReset = true;
            } else {
                isAllReset = false;
                break;
            }
        }
        return isAllReset;
    }

    public static boolean checkRemoveButtonChangesToAdd(WebElement item) {
        String buttonText = item.findElement(By.className("btn_inventory")).getText();
        return !buttonText.equals("REMOVE");
    }

    public boolean checkItemsOrderedAtoZ() {
        ArrayList<String> itemName = new ArrayList<>();
        for (WebElement item : getAllItems())  itemName.add(item.findElement(By.className("inventory_item_name")).getText());

        ArrayList<String> sortedList = new ArrayList<>(itemName);
        Collections.sort(sortedList);
        return itemName.equals(sortedList);
    }
}
