package org.jstefek.seleniumPlayground.pages.checker;

import static org.mockito.Mockito.verify;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.mockito.Mockito;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimplePageLoadedCheckerTest {

    private AbstractPage page;
    private SimplePageLoadedChecker splc;

    @BeforeMethod
    public void setup() {
        splc = new SimplePageLoadedChecker();
        page = Mockito.mock(AbstractPage.class);
    }

    @Test
    public void testWaitForPageToLoad_invokesPageWaitMethod() {
        splc.waitForPageToLoad(page);
        verify(page).waitForLoad();
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testWaitForPageToLoad_waitMethodTimeouts_exceptionGoesThrough() {
        Mockito.doThrow(new TimeoutException()).when(page).waitForLoad();

        splc.waitForPageToLoad(page);
    }

}
