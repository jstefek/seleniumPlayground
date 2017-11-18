package org.jstefek.seleniumPlayground.pages.instanciator;

import javax.inject.Inject;
import org.jstefek.seleniumPlayground.pages.AbstractPage;
import org.jstefek.seleniumPlayground.pages.PageConfiguration;

class SimplePageInstanciator implements PageInstanciator {

    private final PageConfiguration pc;

    @Inject
    SimplePageInstanciator(PageConfiguration pc) {
        this.pc = pc;
    }

    @Override
    public <T extends AbstractPage> T instantiatePage(Class<T> pageKlass) {
        try {
            return pageKlass.getConstructor(PageConfiguration.class).newInstance(pc);
        } catch (Exception ex) {
            throw new RuntimeException("Page could not be instantiated", ex);
        }
    }

}
