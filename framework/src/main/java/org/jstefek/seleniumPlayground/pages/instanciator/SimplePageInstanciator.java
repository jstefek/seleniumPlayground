package org.jstefek.seleniumPlayground.pages.instanciator;

import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.factory.PageFactory;
import org.openqa.selenium.WebDriver;

public class SimplePageInstanciator implements PageInstanciator {

    @Override
    public <T extends AbstractPage> T instantiatePage(Class<T> pageKlass, WebDriver browser, PageFactory pf) {
        try {
            return pageKlass.getConstructor(WebDriver.class, PageFactory.class).newInstance(browser, pf);
        } catch (Exception ex) {
            throw new RuntimeException("Page could not be instantiated", ex);
        }
    }

}
