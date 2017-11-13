package org.jstefek.seleniumPlayground.pages.checker;

import org.jstefek.seleniumPlayground.pages.AbstractPage;

public interface PageChecker {

    <T extends AbstractPage> void checkPage(T page);
}
