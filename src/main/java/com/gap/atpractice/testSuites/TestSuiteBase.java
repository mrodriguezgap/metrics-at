package com.gap.atpractice.testSuites;

import com.gap.atpractice.selenium.SeleniumBase;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;

/**
 * Created by auto on 25/05/17.
 */
public class TestSuiteBase {


    public TestSuiteBase() {
    }

    @BeforeGroups
    public static WebDriver init(String browser, boolean capabilities) throws Exception {
        if (capabilities) {
            return initWithCapabilities(browser);
        } else {
            return initWithoutCapabilities(browser);
        }
    }

    @AfterGroups
    public static void finish() throws InterruptedException {
        Thread.sleep(5000);
    }

    private static WebDriver initWithCapabilities(String browser) throws Exception {
        SeleniumBase base = new SeleniumBase();
        try {
            return base.setup(browser, true);
        } catch (Exception e) {
            throw e;
        }
    }

    private static WebDriver initWithoutCapabilities(String browser) throws Exception {
        SeleniumBase base = new SeleniumBase();
        try {
            return base.setup(browser, false);
        } catch (Exception e) {
            throw e;
        }
    }
}
