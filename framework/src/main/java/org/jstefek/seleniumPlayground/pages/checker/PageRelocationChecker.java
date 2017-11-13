package org.jstefek.seleniumPlayground.pages.checker;

import org.jstefek.seleniumPlayground.pages.AbstractPage;

/**
 * Checks change of page's url/title after page is loaded.
 */
public interface PageRelocationChecker {

    <T extends AbstractPage> void checkPageRelocated(T page);
}
