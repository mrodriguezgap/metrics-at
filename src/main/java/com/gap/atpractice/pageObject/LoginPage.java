package com.gap.atpractice.pageObject;

import com.gap.atpractice.botStyle.BotStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by auto on 15/05/17.
 */
public class LoginPage {

    // paths
    private static final By BUTTONLOGINPATH = By.xpath("//*/input[contains(@class,'button')]");
    private static final By USERNAMEPATH = By.xpath("//input[@id='login']");
    private static final By PASSWORDPATH = By.xpath("//input[@id='password']");


    private static final long TIMEOUT = 10;

    private WebDriver driver;
    private BotStyle botDriver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.botDriver = new BotStyle(driver);
    }

    private WebElement wait(By locator) {
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void sendUserName(String username) {
        wait(USERNAMEPATH).click();
        wait(USERNAMEPATH).sendKeys(username);
    }

    private void sendUserPassword(String password) {
        wait(PASSWORDPATH).click();
        wait(PASSWORDPATH).sendKeys(password);
    }

    private void clickLogin() throws Exception {
        WebElement buttonLogin = wait(BUTTONLOGINPATH);
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            throw new Exception();
        }
    }

    // Public elements

    public void goToLoginPage(String url) {
        this.driver.get(url);
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public HomePage login(String username, String password) throws Exception {
        sendUserName(username);
        sendUserPassword(password);
        clickLogin();
        return new HomePage(this.driver);
    }

    // BotStyle

    public HomePage botLogin(String username, String password) throws Exception {
        botDriver.type(USERNAMEPATH, username);
        botDriver.type(PASSWORDPATH, password);
        botDriver.click(BUTTONLOGINPATH);
        return new HomePage(this.driver);
    }

}
