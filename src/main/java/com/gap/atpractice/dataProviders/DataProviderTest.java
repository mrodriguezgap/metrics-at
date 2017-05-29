package com.gap.atpractice.dataProviders;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

/**
 * Created by auto on 29/05/17.
 */
public class DataProviderTest {

    @DataProvider(name = "dataProviderUser")
    public static Object[][] dataProviderUser(Method m) {
        System.out.println(String.format("Data Provider name: %s", m.getName()));

        return new Object[][]{
                {"test1", "test1"},
                {"test2", "test2"}
        };
    }
}
