package org.jstefek.seleniumPlayground.pages.checker;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import java.util.function.Function;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.checker.annotation.PageLocation;

public class PageCheckersModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<PageChecker[]>() {
        }).toProvider(PageCheckersProvider.class).in(Singleton.class);

        bind(new TypeLiteral<Function<Class<? extends AbstractPage>, PageLocation>>() {
        }).to(ClassToPageLocationFunction.class).in(Singleton.class);
    }

}
