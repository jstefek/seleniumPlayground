package org.jstefek.seleniumPlayground.pages.instanciator;

import org.jstefek.seleniumPlayground.pages.AbstractPage;

/**
 * Creates instances of pages.
 */
public interface PageInstanciator {

    /**
     * Instantiates a page object with given parameters.
     *
     * @param <T> type of the page
     * @param pageKlass class of the page
     * @return instantiated page or throws a RuntimeException when there was a problem with creating new instance of the page
     * class
     */
    <T extends AbstractPage> T instantiatePage(Class<T> pageKlass);
}
