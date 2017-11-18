package org.jstefek.seleniumPlayground.pages.checker;

import java.util.function.Function;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;

class ClassToPageLocationFunction implements Function<Class<? extends AbstractPage>, PageLocation> {

    @Override
    public PageLocation apply(Class<? extends AbstractPage> t) {
        return t.getClass().getAnnotation(PageLocation.class);
    }

}
