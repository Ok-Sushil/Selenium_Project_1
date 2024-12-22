package org.page.login;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FinalLoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By USERNAME_FIELD = By.xpath("//input[@formcontrolname='email']");
    private final By PASSWORD_FIELD = By.xpath("//input[@formcontrolname='password']");
    private final By LOGIN_BUTTON = By.xpath("//button[@class='submit']");
    private final By PROFILE_DROPDOWN = By.xpath("//span[@id='dropdownBasic1']");
    private final By LOGOUT_BUTTON = By.xpath("//ul[@class='dd-menu dropdown-menu show']//li[@class='dropdown-item logout cursor']");

    public FinalLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void login(String email, String password) {
        try {
            System.out.println("Trying login with valid credentials: " + email);

            WebElement usernameField = waitForVisibility(USERNAME_FIELD);
            usernameField.clear();
            usernameField.sendKeys(email);

            WebElement passwordField = waitForVisibility(PASSWORD_FIELD);
            passwordField.clear();
            passwordField.sendKeys(password);

            WebElement loginButton = waitForClickability(LOGIN_BUTTON);
            loginButton.click();
            System.out.println("Login button clicked");

        } catch (TimeoutException e) {
            System.out.println("Login elements not found within the specified time");
            throw e;
        } catch (Exception e) {
            System.out.println("An error occurred during login");
            throw e;
        }
    }

    public boolean isLoginSuccessful() {
        try {
            System.out.println("Validating that user is successfully logged in");
            wait.until(ExpectedConditions.urlContains("property-search-new"));
            return true;
        } catch (Exception e) {
            System.out.println("Login failed. The expected page did not load.");
            return false;
        }
    }

    public void logout() {
        try {
            System.out.println("Attempting to logout");

            boolean isLoggedIn = isUserLoggedIn();

            if (!isLoggedIn) {
                System.out.println("User is already logged out.");
                return;
            }

            WebElement profileDropdown = waitForClickability(PROFILE_DROPDOWN);
            profileDropdown.click();

            WebElement logoutButton = waitForClickability(LOGOUT_BUTTON);
            logoutButton.click();
            System.out.println("User logged out successfully");

        } catch (TimeoutException e) {
            System.err.println("Logout elements not found within the specified time.");
            throw e;
        } catch (Exception e) {
            System.err.println("An error occurred during logout: " + e.getMessage());
            throw e;
        }
    }

    private boolean isUserLoggedIn() {
        try {
            WebElement profileDropdown = waitForVisibility(PROFILE_DROPDOWN);
            return profileDropdown.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("User is not logged in.");
            return false;
        }
    }
}
