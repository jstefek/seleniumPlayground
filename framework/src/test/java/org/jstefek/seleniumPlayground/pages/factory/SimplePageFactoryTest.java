package org.jstefek.seleniumPlayground.pages.factory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.PageLoadedChecker;
import org.jstefek.seleniumPlayground.pages.checker.PageRelocationChecker;
import org.jstefek.seleniumPlayground.pages.instanciator.PageInstanciator;
import org.jstefek.seleniumPlayground.pages.intialize.PageInitializer;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SimplePageFactoryTest {

    @Test
    public void testInitializePage() {
        WebDriver browser = Mockito.mock(WebDriver.class);
        PageLoadedChecker checker = Mockito.mock(PageLoadedChecker.class);
        PageInitializer initializer = Mockito.mock(PageInitializer.class);
        PageInstanciator instanciator = Mockito.mock(PageInstanciator.class);
        PageRelocationChecker relocationChecker = Mockito.mock(PageRelocationChecker.class);
        AbstractPage page = Mockito.mock(AbstractPage.class);

        SimplePageFactory spf = new SimplePageFactory(initializer, instanciator, checker, relocationChecker);

        when(instanciator.instantiatePage(AbstractPage.class, browser, spf)).thenReturn(page);
        spf.initializePage(AbstractPage.class, browser);

        verify(instanciator).instantiatePage(AbstractPage.class, browser, spf);
        verify(initializer).initializePage(page, browser);
        verify(checker).waitForPageToLoad(page);
        verify(relocationChecker).checkPageRelocated(page);
    }

}
