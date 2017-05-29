package com.gap.atpractice.testSuites;

import com.gap.atpractice.pageFactory.HomePageFactory;
import com.gap.atpractice.pageFactory.LoginPageFactory;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.selenium.SeleniumBase;
import org.openqa.selenium.WebDriver;

/**
 * Created by auto on 06/04/17.
 */
public class LoginTest {

    // paths

    private static final String LOGINPATH = "https://auto3ss-staging7.gradesfirst.com/";
    private static final String FILEPATH = "./src/main/java/com/gap/atpractice/resources/screenshot1.png";
    private static final String USERNAME = "rhunt";
    private static final String PASSWORD = "pass1234";

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static LoginPageFactory loginPageFactory;
    private static HomePageFactory homePageFactory;
    private static HomePage homePage;

    public static void main(String[] args) {

        try {
            driver = init();
            loginPage = new LoginPage(driver);

            loginPageFactory = new LoginPageFactory(driver);

            testLoginPageObject(USERNAME, PASSWORD);
            //testLoginPageObject(USERNAME, PASSWORD);

            //testPageFactory(LOGINPATH);
           // testLoginPageFactory(USERNAME, PASSWORD);

            testLoginBotStyle(USERNAME, PASSWORD);

            Thread.sleep(5000);
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static WebDriver init() throws Exception {
        SeleniumBase base = new SeleniumBase();
        try {
            return base.setup("Chrome", false);
        } catch (Exception e) {
            throw e;
        }
    }

    // Page Object

    private static void testLoginPageObject(String userName, String password) throws Exception {
        try {
            loginPage.get();
            System.out.println(loginPage.getPageTitle());
            homePage = loginPage.login(userName, password);
            if (homePage.checkHomePage()) {
                System.out.println(homePage.getPageHeader());
            } else {
                System.out.println("Home page not found!!!");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // Page Factory

    private static void testPageFactory(String url) {
        loginPageFactory.goToLoginPage(url);
        System.out.println(loginPageFactory.getPageTitle());
    }

    private static void testLoginPageFactory(String username, String password) throws Exception {
        try {
            homePageFactory = loginPageFactory.login(username, password);
            if (homePageFactory.checkHomePage()) {
                System.out.println(homePageFactory.getPageHeader());
            } else {
                System.out.println("Home page not found!!!");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    // BotStyle

    private static void testLoginBotStyle(String userName, String password) throws Exception {
        try {
            homePage = loginPage.botLogin(userName, password);
            if (homePage.checkHomePage()) {
                System.out.println(homePage.getPageHeader());
            } else {
                System.out.println("Home page not found!!!");
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
