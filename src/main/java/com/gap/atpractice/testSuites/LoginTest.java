package com.gap.atpractice.testSuites;

import com.gap.atpractice.selenium.SeleniumBase;
import com.gap.atpractice.utils.TakeScreenshots;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by auto on 06/04/17.
 */
public class LoginTest {

    // paths
    private static final String LOGINPATH = "https://auto3ss-staging7.gradesfirst.com/";
    private static final By BUTTONLOGINPATH = By.xpath("//*/input[contains(@class,'button')]");
    private static final By USERNAMEPATH = By.xpath("//input[@id='login']");
    private static final By PASSWORDPATH = By.xpath("//input[@id='password']");
    private static final By HEADERHOME = By.xpath(".//*/h1/div[contains(@class,'current-role')]");

    private static final String FILEPATH = "./src/main/java/com/gap/atpractice/resources/screenshot1.png";

    private static final String USERNAME = "rhunt";
    private static final String PASSWORD = "pass1234";

    private static final long TIMEOUT = 10;


    public static void main(String[] args) {

        SeleniumBase seleniumBase = new SeleniumBase();
        WebDriver driver = null;

        testWithChrome(driver, seleniumBase);


    }

    private static WebElement wait(WebDriver driver, By locator) {
        return (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private static void sendUserName(WebDriver driver) {
        wait(driver, USERNAMEPATH).click();
        wait(driver, USERNAMEPATH).sendKeys(USERNAME);
    }

    private static void sendUserPassword(WebDriver driver) {
        wait(driver, PASSWORDPATH).click();
        wait(driver, PASSWORDPATH).sendKeys(PASSWORD);
    }

    private static void clickLogin(WebDriver driver) throws Exception {
        WebElement buttonLogin = wait(driver, BUTTONLOGINPATH);
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            throw new Exception();
        }
    }

    private static boolean checkHomePage(WebDriver driver) {
        WebElement header = wait(driver, HEADERHOME);
        if (header.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    private static void testWithChrome(WebDriver driver, SeleniumBase seleniumBase){
        try {
            //driver = seleniumBase.setup("Chrome");
            driver = seleniumBase.setup("Chrome", true);

            driver.get(LOGINPATH);
            sendUserName(driver);
            sendUserPassword(driver);
            clickLogin(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(5000);  // Let the user actually see something!
            //WebElement we = driver.findElement(By.name("q"));
            //we.sendKeys("Selenium");
            //we.submit();
            Thread.sleep(5000);  // Let the user actually see something!

            if(checkHomePage(driver)) {
                TakeScreenshots.takescreenshot(driver, FILEPATH);
            }

            driver.quit();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
