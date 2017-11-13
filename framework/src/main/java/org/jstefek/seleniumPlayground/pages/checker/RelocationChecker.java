package org.jstefek.seleniumPlayground.pages.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;
import org.jstefek.seleniumPlayground.pages.utils.StringUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Scans given page object's class for {@link org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation PageLocation}
 * annotation, from which it creates conditions to be checked (all non null nor empty values) and then waits for all of them to
 * be met.
 */
public class RelocationChecker implements PageChecker {

    private final Function<Class<? extends AbstractPage>, PageLocation> classToPageLocationFunction;

    public RelocationChecker() {
        this((c) -> c.getClass().getAnnotation(PageLocation.class));
    }

    RelocationChecker(Function<Class<? extends AbstractPage>, PageLocation> classToPageLocationFunction) {
        this.classToPageLocationFunction = classToPageLocationFunction;
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
        if (StringUtils.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.titleIs(value));
        }
        value = location.titleToContain();
        if (StringUtils.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.titleContains(value));
        }
        if (StringUtils.isNotNullNorEmpty(location.titleToMatch())) {
            result.add(d -> d.getTitle().matches(location.titleToMatch()));
        }
        value = location.urlToBe();
        if (StringUtils.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.urlToBe(value));
        }
        value = location.urlToContain();
        if (StringUtils.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.urlContains(value));
        }
        value = location.urlToMatch();
        if (StringUtils.isNotNullNorEmpty(value)) {
            result.add(ExpectedConditions.urlMatches(value));
        }
        return result.toArray(new ExpectedCondition[result.size()]);
    }
}
