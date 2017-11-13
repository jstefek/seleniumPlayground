package org.jstefek.seleniumPlayground.pages.checker;

import static org.mockito.Mockito.verify;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.mockito.Mockito;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageLoadedCheckerTest {

    private AbstractPage page;
    private PageLoadedChecker splc;

    @BeforeMethod
    public void setup() {
        splc = new PageLoadedChecker();
        page = Mockito.mock(AbstractPage.class);
    }

    @Test
    public void testCheckPage_invokesPageWaitMethod() {
        splc.checkPage(page);
        verify(page).waitForLoad();
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testCheckPage_waitMethodTimeouts_exceptionGoesThrough() {
        Mockito.doThrow(new TimeoutException()).when(page).waitForLoad();
        splc.checkPage(page);
    }

}
