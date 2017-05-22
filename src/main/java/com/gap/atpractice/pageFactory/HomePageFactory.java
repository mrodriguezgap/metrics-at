package com.gap.atpractice.pageFactory;

import com.gap.atpractice.pageObject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by auto on 15/05/17.
 */
public class HomePageFactory {

    private static final long TIMEOUT = 30;
    private static final long POLL = 5;

    // paths
    @FindBy(xpath = ".//*/h1/div[contains(@class,'current-role')]")
    private WebElement headerHome;

    private WebDriver driver;
    private HomePage homePage;

    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
        PageFactory.initElements(driver, this);
    }

    private Wait wait(long timeout, long poll) {
        return new FluentWait(driver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(poll, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    // implicit wait
    public void waitForPageTitle(long timeToWaitSecs, final String title){

        (new WebDriverWait(this.driver, timeToWaitSecs)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(title.toLowerCase());
            }
        });
    }

    public boolean checkHomePage() {
        wait(TIMEOUT, POLL);
        if (headerHome.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public String getPageTitle() {
        wait(TIMEOUT, POLL);
        return this.driver.getTitle();
    }

    public String getPageHeader() {
        wait(TIMEOUT, POLL);
        waitForPageTitle(TIMEOUT, this.driver.getTitle()); // not sure this will work
        return headerHome.getText();
    }
}
