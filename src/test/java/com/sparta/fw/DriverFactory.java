package com.sparta.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public static WebDriver getDriver(DriverType type){
        WebDriver driver;

        switch (type){
            case CHROME -> driver = new ChromeDriver();
            case FIREFOX -> driver = new FirefoxDriver();
            //case CHROMIUM -> driver = new ChromiumDriver(); //has protected access?
            case SAFARI -> driver =  new SafariDriver();
            case IE -> driver = new InternetExplorerDriver();
            default -> driver = new EdgeDriver();
        }
        return driver;
    }
}
