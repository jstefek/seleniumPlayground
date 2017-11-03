package org.jstefek.seleniumPlayground.browser;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BrowserTypeTest {

    @DataProvider(name = "data")
    public static Object[][] data() {
        return new Object[][]{
            {"", BrowserType.UNKNOWN},
            {"ff", BrowserType.FIREFOX},
            {"Ff", BrowserType.FIREFOX},
            {"firefox", BrowserType.FIREFOX},
            {"Firefox", BrowserType.FIREFOX},
            {"fireFox", BrowserType.FIREFOX},
            {"FiReFoX", BrowserType.FIREFOX},
            {"cr", BrowserType.CHROME},
            {"cR", BrowserType.CHROME},
            {"chrome", BrowserType.CHROME},
            {"Chrome", BrowserType.CHROME},
            {"Chrobak", BrowserType.UNKNOWN},
            {"frajerfox", BrowserType.UNKNOWN},
            {".*", BrowserType.UNKNOWN},
            {"*", BrowserType.UNKNOWN}
        };

    }

    @Test(dataProvider = "data")
    public void testParseBrowser(String textToParse, BrowserType result) {
        Assert.assertEquals(BrowserType.parseBrowser(textToParse), result);
    }

}
