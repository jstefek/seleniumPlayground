package org.jstefek.seleniumPlayground.browser.factory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jstefek.seleniumPlayground.browser.BrowserType;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.ChromeInstanciator;
import org.jstefek.seleniumPlayground.browser.factory.instanciator.FirefoxInstanciator;
import org.mockito.Mockito;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleBrowserFactoryTest {

    private SimpleBrowserFactory browserFactory;
    private ChromeDriver chrome;
    private ChromeInstanciator cr;
    private FirefoxInstanciator ff;
    private FirefoxDriver firefox;

    @BeforeMethod
    public void setup() {
        cr = Mockito.mock(ChromeInstanciator.class);
        ff = Mockito.mock(FirefoxInstanciator.class);
        firefox = Mockito.mock(FirefoxDriver.class);
        chrome = Mockito.mock(ChromeDriver.class);
        when(cr.startBrowser()).thenReturn(chrome);
        when(ff.startBrowser()).thenReturn(firefox);
        browserFactory = new SimpleBrowserFactory(cr, ff);
    }

    @Test
    public void testStartBrowser_startChrome_isStarted() {
        Assert.assertTrue(browserFactory.startBrowser(BrowserType.CHROME) instanceof ChromeDriver);
        verify(cr, times(1)).startBrowser();
        verify(ff, times(0)).startBrowser();
    }

    @Test
    public void testStartBrowser_startFirefox_isStarted() {
        Assert.assertTrue(browserFactory.startBrowser(BrowserType.FIREFOX) instanceof FirefoxDriver);
        verify(cr, times(0)).startBrowser();
        verify(ff, times(1)).startBrowser();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testStartBrowser_startUnknown_throwsException() {
        browserFactory.startBrowser(BrowserType.UNKNOWN);
    }

}
