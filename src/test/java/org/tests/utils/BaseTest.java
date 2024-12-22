package org.tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
