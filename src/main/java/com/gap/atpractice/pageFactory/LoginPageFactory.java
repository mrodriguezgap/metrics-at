package com.gap.atpractice.pageFactory;

import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by auto on 15/05/17.
 */
public class LoginPageFactory {

    private static final long TIMEOUT = 30;
    private static final long POLL = 5;

    // paths
    @FindBy(xpath = "//*/input[contains(@class,'button')]")
    private WebElement buttonLogin;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, this);
    }

    private Wait wait(long timeout, long poll) {
        return new FluentWait(driver)
        .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(poll, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    private void sendUserName(String username) {
        wait(TIMEOUT, POLL);
        userNameField.click();
        userNameField.sendKeys(username);
    }

    private void sendUserPassword(String password) {
        wait(TIMEOUT, POLL);
        passwordField.click();
        passwordField.sendKeys(password);
    }

    private void clickLogin() throws Exception {
        wait(TIMEOUT, POLL);
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            throw new Exception();
        }
    }

    public void goToLoginPage(String url) {
        loginPage.goToLoginPage(url);
    }

    public String getPageTitle() {
        wait(TIMEOUT, POLL);
        return this.driver.getTitle();
    }

    public HomePageFactory login(String username, String password) throws Exception {
        sendUserName(username);
        sendUserPassword(password);
        clickLogin();
        return new HomePageFactory(this.driver);
    }
}
