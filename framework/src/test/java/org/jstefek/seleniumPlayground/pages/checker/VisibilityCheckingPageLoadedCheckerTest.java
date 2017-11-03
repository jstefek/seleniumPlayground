package org.jstefek.seleniumPlayground.pages.checker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Supplier;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.mockito.Mockito;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisibilityCheckingPageLoadedCheckerTest {

    private AbstractPage page;
    private VisibilityCheckingPageLoadedChecker pageLoader;
    private FluentWait<WebDriver> wait;

    @BeforeMethod
    public void setup() {
        pageLoader = new VisibilityCheckingPageLoadedChecker();
        page = Mockito.mock(AbstractPage.class);
        wait = Mockito.mock(FluentWait.class);
        when(page.createWait()).thenReturn(wait);
        when(wait.withMessage(any(Supplier.class))).thenReturn(wait);
        when(wait.until(any())).thenReturn(true);
    }

    @Test
    public void testWaitForPageToLoad_invokesCreateWait() {
        pageLoader.waitForPageToLoad(page);
        verify(page, times(1)).createWait();
    }

    @Test
    public void testWaitForPageToLoad_invokesWaitMethod() {
        pageLoader.waitForPageToLoad(page);
        verify(page, times(1)).waitForLoad();
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForPageToLoad_waitMethodTimeouts_exceptionGoesThrough() {
        when(wait.until(any())).thenThrow(new TimeoutException(""));
        pageLoader.waitForPageToLoad(page);
    }

}
