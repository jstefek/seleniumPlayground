package org.jstefek.seleniumPlayground.pages.checker;

import org.jstefek.seleniumPlayground.pages.AbstractPage;

public interface PageLoadedChecker {

    /**
     * Waits for page to load.
     *
     * @param <T> type of page
     * @param page instance of page
     */
    <T extends AbstractPage> void waitForPageToLoad(T page);

}
