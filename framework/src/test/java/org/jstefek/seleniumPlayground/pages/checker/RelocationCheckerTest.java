package org.jstefek.seleniumPlayground.pages.checker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Function;
import java.util.function.Supplier;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;
import org.jstefek.seleniumPlayground.pages.utils.StringUtilsService;
import org.mockito.Mockito;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RelocationCheckerTest {

    private RelocationChecker checker;
    private AbstractPage page;
    private PageLocation pageLocation;
    private FluentWait<WebDriver> wait;
    private Function<Class<? extends AbstractPage>, PageLocation> function;
    private StringUtilsService sus;

    @BeforeMethod
    public void setup() {
        wait = Mockito.mock(FluentWait.class);
        when(wait.withMessage(any(Supplier.class))).thenReturn(wait);
        when(wait.until(any())).thenReturn(true);

        page = Mockito.mock(AbstractPage.class);
        when(page.createWait()).thenReturn(wait);

        pageLocation = Mockito.mock(PageLocation.class);
        function = Mockito.mock(Function.class);
        when(function.apply(any())).thenReturn(pageLocation);

        sus = Mockito.mock(StringUtilsService.class);
        when(sus.isNotNullNorEmpty(any())).thenReturn(Boolean.TRUE);

        checker = new RelocationChecker(function, sus);
    }

    @Test
    public void testCheckPage_annotationNotPresent_noOperation() {
        when(function.apply(page.getClass())).thenReturn(null);
        checker.checkPage(page);
        verify(page, times(0)).createWait();
    }

    @Test
    public void testCheckPage_annotationPresent_createsWait() {
        checker.checkPage(page);
        verify(page, times(1)).createWait();
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void testCheckPage_waitMethodTimeouts_exceptionGoesThrough() {
        when(wait.until(any())).thenThrow(new TimeoutException(""));
        checker.checkPage(page);
    }

}
