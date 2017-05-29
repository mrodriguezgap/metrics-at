package com.gap.atpractice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by auto on 15/05/17.
 */
public class LoginPage extends PageBase {

    // paths
    private static final By BUTTONLOGINPATH = By.xpath("//*/input[contains(@class,'button')]");
    private static final By USERNAMEPATH = By.xpath("//input[@id='login']");
    private static final By PASSWORDPATH = By.xpath("//input[@id='password']");
    private static final String PATH = "/session/new";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private void sendUserName(String username) {
        super.wait(USERNAMEPATH).click();
        super.wait(USERNAMEPATH).sendKeys(username);
    }

    private void sendUserPassword(String password) {
        super.wait(PASSWORDPATH).click();
        super.wait(PASSWORDPATH).sendKeys(password);
    }

    private void clickLogin() throws Exception {
        WebElement buttonLogin = super.wait(BUTTONLOGINPATH);
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            throw new Exception();
        }
    }

    // Public elements

    public void goToLoginPage(String url) {
        super.goToPage(url);
    }

    public String getPageTitle() {
        return super.getPageTitle();
    }

    public HomePage login(String username, String password) throws Exception {
        sendUserName(username);
        sendUserPassword(password);
        clickLogin();
        return new HomePage(this.driver);
    }

    // BotStyle

    public HomePage botLogin(String username, String password) throws Exception {
        super.botDriver.type(USERNAMEPATH, username);
        super.botDriver.type(PASSWORDPATH, password);
        super.botDriver.click(BUTTONLOGINPATH);
        return new HomePage(this.driver);
    }

    @Override
    protected void load() {
        super.driver.get(super.createURL(PATH));
    }

    @Override
    protected void isLoaded() throws Error {
        super.driver.get(URL);
        JavascriptExecutor js = (JavascriptExecutor) super.driver;
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            System.out.println("Overview page is loaded");
        }
    }
}
