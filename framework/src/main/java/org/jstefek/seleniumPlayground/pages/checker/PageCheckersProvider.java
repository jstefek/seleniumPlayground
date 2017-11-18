package org.jstefek.seleniumPlayground.pages.checker;

import javax.inject.Inject;
import javax.inject.Provider;

class PageCheckersProvider implements Provider<PageChecker[]> {

    private final PageLoadedChecker plc;
    private final RelocationChecker rc;
    private final VisibilityChecker vc;

    @Inject
    PageCheckersProvider(PageLoadedChecker plc, RelocationChecker rc, VisibilityChecker vc) {
        this.plc = plc;
        this.rc = rc;
        this.vc = vc;
    }

    @Override
    public PageChecker[] get() {
        return new PageChecker[]{plc, rc, vc};
    }

}
