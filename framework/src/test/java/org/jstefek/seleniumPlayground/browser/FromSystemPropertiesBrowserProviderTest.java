package org.jstefek.seleniumPlayground.browser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

import java.util.function.Supplier;
import org.jstefek.seleniumPlayground.browser.factory.BrowserFactory;
import org.mockito.Mockito;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FromSystemPropertiesBrowserProviderTest {

    private BrowserFactory bf;
    private Supplier<BrowserType> browserTypeSupplier;
    private ChromeDriver chromeDriver;
    private FirefoxDriver firefoxDriver;
    private FromSystemPropertiesBrowserProvider provider;

    @BeforeMethod
    public void setup() {
        chromeDriver = Mockito.mock(ChromeDriver.class);
        firefoxDriver = Mockito.mock(FirefoxDriver.class);
        bf = Mockito.mock(BrowserFactory.class);
        browserTypeSupplier = Mockito.mock(Supplier.class);
        provider = new FromSystemPropertiesBrowserProvider(bf, browserTypeSupplier);

        when(bf.startBrowser(BrowserType.FIREFOX)).thenReturn(firefoxDriver);
        when(bf.startBrowser(BrowserType.CHROME)).thenReturn(chromeDriver);
        when(bf.startBrowser(BrowserType.UNKNOWN)).thenThrow(new UnsupportedOperationException());
    }

    @Test
    public void testGetBrowser_browserIsSet_browserIsStartedOnlyOnce() {
        when(browserTypeSupplier.get()).thenReturn(BrowserType.FIREFOX);
        provider.getBrowser();
        verify(bf, times(1)).startBrowser(any());
        provider.getBrowser();
        verify(bf, times(1)).startBrowser(any());
    }

    @Test
    public void testGetBrowser_chromeIsSet_chromeIsReturned() {
        when(browserTypeSupplier.get()).thenReturn(BrowserType.CHROME);
        assertTrue(provider.getBrowser() instanceof ChromeDriver);
    }

    @Test
    public void testGetBrowser_firefoxIsSet_firefoxIsReturned() {
        when(browserTypeSupplier.get()).thenReturn(BrowserType.FIREFOX);
        assertTrue(provider.getBrowser() instanceof FirefoxDriver);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testGetBrowser_unknownIsSet_throwsException() {
        when(browserTypeSupplier.get()).thenReturn(BrowserType.UNKNOWN);
        provider.getBrowser();
    }
}
