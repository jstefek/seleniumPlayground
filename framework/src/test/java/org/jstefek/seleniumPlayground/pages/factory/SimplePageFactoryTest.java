package org.jstefek.seleniumPlayground.pages.factory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.PageLoadedChecker;
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
        AbstractPage ap = Mockito.mock(AbstractPage.class);

        SimplePageFactory spf = new SimplePageFactory(checker, initializer, instanciator);

        when(instanciator.instantiatePage(AbstractPage.class, browser, spf)).thenReturn(ap);
        spf.initializePage(AbstractPage.class, browser);

        verify(instanciator).instantiatePage(AbstractPage.class, browser, spf);
        verify(initializer).initializePage(ap, browser);
        verify(checker).waitForPageToLoad(ap);
    }

}
