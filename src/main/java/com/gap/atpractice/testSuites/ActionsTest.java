package com.gap.atpractice.testSuites;

import com.gap.atpractice.selenium.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by auto on 04/05/17.
 */
public class ActionsTest {

    private static final long TIMEOUT = 10;

    private static final String URLPATH = "https://jsfiddle.net/L6qggtub/2/show/";
    private static final By DOUBLECLICK = By.xpath("//p[@id='double_click']");
    private static final By MOUSEHOVER = By.xpath(".//*/div/button[contains(@class,'dropbtn')]");
    private static final By MENUELEMENTS = By.xpath("//*/div[@class='dropdown-content']/a");
    private static final By TEXTAREA = By.xpath("//textarea");
    private static final By DRAGGABLE = By.xpath("//p[@id='draggable']");
    private static final By DROPPABLE = By.xpath("//p[@id='droppable']");


    public static void main(String[] args) {

        SeleniumBase base = new SeleniumBase();

        try {
            WebDriver driver = base.setup("Chrome", false);
            driver.get(URLPATH);
            WebElement iframe = driver.findElement(By.cssSelector("iframe"));
            driver.switchTo().frame(iframe); // Switch to desired iframe
            //driver.switchTo().defaultContent(); // Return to top iframe

            Actions action = new Actions(driver);
            //
            // Methods
            //perform_doubleclick(driver, action);
            perform_hover(driver, action);
            //
            Thread.sleep(5000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void perform_doubleclick(WebDriver driver, Actions action){
        action.moveToElement(driver.findElement(DOUBLECLICK));
        action.doubleClick().perform();
    }

    private static void perform_hover(WebDriver driver, Actions action){
        action.moveToElement(driver.findElement(MOUSEHOVER)).perform();
        List<WebElement> items = driver.findElements(MENUELEMENTS);
        for (int i = 0; i<items.size(); i++){
            action.moveToElement(items.get(i)).perform();
        }
    }

    private static WebElement wait(WebDriver driver, By locator) {
        return (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
