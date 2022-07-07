package com.sparta.fw.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwagCartPage {

    private final WebDriver driver;
    private String password = "secret_sauce";
    private String usernameStandard = "standard_user";
    private String userNameLocked = "locked_out_user";
    private String usernamePerformance = "performance_glitch_user";

    private By burgerMenuLink = new By.ById("react-burger-menu-btn");

    public SwagCartPage(WebDriver driver) {
        this.driver = driver;

        driver.get("https://www.saucedemo.com/cart.html");
        driver.findElement(By.id("user-name")).sendKeys(usernameStandard);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();

    }
    //interactions with other pages

    public void clickContinueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
    }

    public SwagHomePage goToHomePage() {
        driver.findElement(By.id("continue-shopping")).click();
        return new SwagHomePage(driver);
    }

    public void checkout() {
        driver.findElement(By.id("checkout")).click();
    }

    public SwagCheckoutPage goToCheckoutPage() {
        driver.findElement(By.id("checkout")).click();
        return new SwagCheckoutPage(driver);
    }


    public void goToAboutPage() {
        driver.findElement(burgerMenuLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));
        driver.findElement(By.id("about_sidebar_link")).click();
    }

    public void goToResetAppState() {
        driver.findElement(burgerMenuLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset_sidebar_link")));
        driver.findElement(By.id("reset_sidebar_link")).click();
    }

    public List<WebElement> cartItemAdded() {
        return driver.findElements(By.className("cart_item"));
    }

    public boolean checkAllItemsCorrectlyAddedToCart() {
        boolean addsCorrectly = false;
        ArrayList<String> itemsInCart = new ArrayList<>();
        for (WebElement element : cartItemAdded()) {
            itemsInCart.add(element.findElement(By.className("inventory_item_name")).getText());
        }
        SwagHomePage homePage = new SwagHomePage(driver);
        for (WebElement element : homePage.getAllItems()) {
            String itemName = element.findElement(By.className("inventory_item_name")).getText();
            if (itemsInCart.contains(itemName)) {
                addsCorrectly = true;
            } else {
                addsCorrectly = false;
                break;
            }
        }
        return addsCorrectly;
    }

    public boolean checkCorrectQuantityOfAllItemsAddedToCart() {
        boolean addsCorrectQuantity = false;
        ArrayList<String> itemsQuantity = new ArrayList<>();
        for (WebElement element : cartItemAdded()) {
            itemsQuantity.add(element.findElement(By.className("cart_quantity")).getText());
        }
        for (String item : itemsQuantity) {
            if (item.equals("1")) {
                addsCorrectQuantity = true;
            } else {
                addsCorrectQuantity = false;
                break;
            }
        }
        return addsCorrectQuantity;
    }

    public String[] splitAtSpace(String string) {
        String lowercaseString = string.toLowerCase();
        String[] split = lowercaseString.split("\\s+");
        return split;
    }

    public String getRemoveId(String itemName){

        String[] itemWords = splitAtSpace(itemName);
        String removeId;

        //could refine the below for more words

        if(itemWords.length ==1){
            removeId = "remove-sauce-labs-"+itemName;
        } else if (itemWords.length == 2) {
            removeId = "remove-sauce-labs-"+itemWords[0] + itemWords[1];
        } else {
            removeId = "remove_sauce-labs"+itemWords[0]+itemWords[1]+itemWords[2];
        }
        return removeId;
    }



  public void removeItemFromCart(){
      for (WebElement element : cartItemAdded()) {
          String elementName = element.findElement(By.className("cart_quantity")).getText();
          String removeId = getRemoveId(elementName);
          driver.findElement(By.id(removeId)).click();
      }
    }



    public boolean checkItemsRemovedFromCart() {
        removeItemFromCart();
        boolean removeFromCart = false;
        ArrayList<String> itemsQuantity = new ArrayList<>();
        for (WebElement element : cartItemAdded()) {
            itemsQuantity.add(element.findElement(By.className("cart_quantity")).getText());
        }
        if (itemsQuantity.size() ==0){
            removeFromCart = true;
        }
        return removeFromCart;
    }

    /*
    public WebDriver updateQuantity(){
        driver.findElement(By.className("cart_quantity")).click();
        driver.findElement(By.className("cart_quantity")).sendKeys(Keys.DELETE, "20");

    }

     */





}

