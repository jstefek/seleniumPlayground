package org.jstefek.seleniumPlayground.pages.checker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.function.Supplier;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.WaitForVisibilityAfterLoad;
import org.jstefek.seleniumPlayground.pages.utils.ReflectionUtilsService;
import org.mockito.Mockito;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisibilityCheckerTest {

    private AbstractPage page;
    private VisibilityChecker pageLoader;
    private FluentWait<WebDriver> wait;
    private ReflectionUtilsService service;
    private List<WebElement> elements;

    @BeforeMethod
    public void setup() {
        service = Mockito.mock(ReflectionUtilsService.class);
        page = Mockito.mock(AbstractPage.class);
        wait = Mockito.mock(FluentWait.class);
        elements = Mockito.mock(List.class);
        when(page.createWait()).thenReturn(wait);
        when(wait.withMessage(any(Supplier.class))).thenReturn(wait);
        when(wait.until(any())).thenReturn(true);
        when(elements.isEmpty()).thenReturn(false);
        when(service.getFieldsValuesWithAnnotationAndOfType(page, WaitForVisibilityAfterLoad.class, WebElement.class)).thenReturn(elements);
        pageLoader = new VisibilityChecker(service);
    }

    @Test
    public void testCheckPage_whenNoElementAnnotated_doesNotInvokeCreateWait() {
        when(elements.isEmpty()).thenReturn(true);
        pageLoader.checkPage(page);
        verify(page, times(0)).createWait();
    }

    @Test
    public void testCheckPage_whenSomeElementsAnnotated_invokesCreateWait() {
        pageLoader.checkPage(page);
        verify(page, times(1)).createWait();
    }

    @Test(expectedExceptions = {TimeoutException.class})
    public void testCheckPage_waitMethodTimeouts_exceptionGoesThrough() {
        when(wait.until(any())).thenThrow(new TimeoutException(""));
        pageLoader.checkPage(page);
    }

}
