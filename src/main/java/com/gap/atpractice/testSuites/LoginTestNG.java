package com.gap.atpractice.testSuites;


import com.gap.atpractice.pageFactory.HomePageFactory;
import com.gap.atpractice.pageFactory.LoginPageFactory;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.pageObject.HomePage;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by auto on 22/05/17.
 */
public class LoginTestNG extends TestSuiteBase{

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static LoginPageFactory loginPageFactory;
    private static HomePageFactory homePageFactory;
    private static HomePage homePage;

    @BeforeGroups("test_001")
    @Parameters({"browserName"})
    private void init(String browser) throws Exception{
        System.out.println("Testing setup...");
        driver = init(browser, false);

        /*try {
            driver = base.setup("Chrome", false);
            loginPage = new LoginPage(driver);
            loginPageFactory = new LoginPageFactory(driver);
        } catch (Exception e) {
            throw e;
        }*/
    }
    /*
     */
    @Test(groups = "test_001")
    private void testPageObject() {
        loginPage = (LoginPage) new LoginPage(driver).get();
        System.out.println(loginPage.getPageTitle());
        Assert.assertEquals(loginPage.getPageTitle(), "Login | SSC Campus", "Login Page Not Found");
    }

    @Test(groups = "test_001")
    @Parameters({"userName", "userPassword"})
    private void testLoginPO(String userName, String password) throws Exception {
        homePage = loginPage.login(userName, password);
        Assert.assertEquals(homePage.checkHomePage(), true, "Home Page not loaded");
        System.out.println(homePage.getPageTitle());
    }

    @AfterGroups("test_001")
    public static void finish() throws InterruptedException {
        TestSuiteBase.finish();
        driver.close();
    }

}
