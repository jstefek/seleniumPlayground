package org.jstefek.seleniumPlayground.pages.factory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.PageChecker;
import org.jstefek.seleniumPlayground.pages.instanciator.PageInstanciator;
import org.jstefek.seleniumPlayground.pages.intialize.PageInitializer;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SimplePageFactoryTest {

    @Test
    public void testInitializePage() {
        WebDriver browser = Mockito.mock(WebDriver.class);
        PageInitializer initializer = Mockito.mock(PageInitializer.class);
        PageInstanciator instanciator = Mockito.mock(PageInstanciator.class);
        PageChecker checker1 = Mockito.mock(PageChecker.class);
        PageChecker checker2 = Mockito.mock(PageChecker.class);
        PageChecker checker3 = Mockito.mock(PageChecker.class);

        AbstractPage page = Mockito.mock(AbstractPage.class);

        SimplePageFactory spf = new SimplePageFactory(instanciator, initializer, checker1, checker2, checker3);

        when(instanciator.instantiatePage(AbstractPage.class, browser, spf)).thenReturn(page);
        spf.initializePage(AbstractPage.class, browser);

        verify(instanciator).instantiatePage(AbstractPage.class, browser, spf);
        verify(initializer).initializePage(page, browser);
        verify(checker1).checkPage(page);
        verify(checker2).checkPage(page);
        verify(checker3).checkPage(page);
    }

}
