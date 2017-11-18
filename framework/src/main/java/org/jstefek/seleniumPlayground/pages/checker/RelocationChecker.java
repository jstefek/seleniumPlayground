package org.jstefek.seleniumPlayground.pages.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.inject.Inject;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;
import org.jstefek.seleniumPlayground.pages.utils.StringUtilsService;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Scans given page object's class for {@link org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation PageLocation}
 * annotation, from which it creates conditions to be checked (all non null nor empty values) and then waits for all of them to
 * be met.
 */
class RelocationChecker implements PageChecker {

    private final Function<Class<? extends AbstractPage>, PageLocation> classToPageLocationFunction;
    private final StringUtilsService sus;

    @Inject
    public RelocationChecker(Function<Class<? extends AbstractPage>, PageLocation> classToPageLocationFunction, StringUtilsService sus) {
        this.classToPageLocationFunction = classToPageLocationFunction;
        this.sus = sus;
    }

    @Override
    public <T extends AbstractPage> void checkPage(T page) {
        PageLocation location = classToPageLocationFunction.apply(page.getClass());
        if (location != null) {
            page.createWait().until(ExpectedConditions.and(getConditions(location)));
        }
    }

    private ExpectedCondition<Boolean>[] getConditions(final PageLocation location) {
        List<ExpectedCondition<Boolean>> result = new ArrayList<>();
        String value;
        value = location.titleToBe();
        if (sus.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.titleIs(value));
        }
        value = location.titleToContain();
        if (sus.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.titleContains(value));
        }
        if (sus.isNotNullNorEmpty(location.titleToMatch())) {
            result.add(d -> d.getTitle().matches(location.titleToMatch()));
        }
        value = location.urlToBe();
        if (sus.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.urlToBe(value));
        }
        value = location.urlToContain();
        if (sus.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.urlContains(value));
        }
        value = location.urlToMatch();
        if (sus.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.urlMatches(value));
        }
        return result.toArray(new ExpectedCondition[result.size()]);
    }
}
