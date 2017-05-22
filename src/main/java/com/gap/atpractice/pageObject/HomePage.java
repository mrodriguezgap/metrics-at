package com.gap.atpractice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by auto on 15/05/17.
 */
public class HomePage {

    private static final By HEADERHOME = By.xpath(".//*/h1/div[contains(@class,'current-role')]");

    private static final long TIMEOUT = 10;

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private WebElement wait(By locator) {
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Public elements

    public boolean checkHomePage() {
        WebElement header = wait(HEADERHOME);
        if (header.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public String getPageTitle()
    {
        return this.driver.getTitle();
    }

    public String getPageHeader(){
        return wait(HEADERHOME).getText();
    }
}
