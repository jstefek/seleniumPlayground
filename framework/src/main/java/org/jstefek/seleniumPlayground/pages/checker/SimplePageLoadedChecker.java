package org.jstefek.seleniumPlayground.pages.checker;

import org.jstefek.seleniumPlayground.pages.AbstractPage;

/**
 * Wait until page is loaded using default page's method {@link AbstractPage#waitForLoad() AbstractPage#waitForLoad()}
 */
public class SimplePageLoadedChecker implements PageLoadedChecker {

    @Override
    public <T extends AbstractPage> void waitForPageToLoad(T page) {
        page.waitForLoad();
    }

}
