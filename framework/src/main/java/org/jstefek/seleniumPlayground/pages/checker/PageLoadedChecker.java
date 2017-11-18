package org.jstefek.seleniumPlayground.pages.checker;

import org.jstefek.seleniumPlayground.pages.AbstractPage;

/**
 * Wait until page is loaded using default page's method {@link AbstractPage#waitForLoad() AbstractPage#waitForLoad()}
 */
class PageLoadedChecker implements PageChecker {

    @Override
    public <T extends AbstractPage> void checkPage(T page) {
        page.waitForLoad();
    }
}
