package org.jstefek.seleniumPlayground.config;

import java.util.concurrent.TimeUnit;

/**
 * Singleton for storing wait times
 */
public enum BaseConfig {
    INSTANCE;

    private final TimeValue baseWaitTimeInMillis = TimeValue.createValue(5000L, TimeUnit.MILLISECONDS);
    private final TimeValue pageLoadWaitTimeInMillis = TimeValue.createValue(10000L, TimeUnit.MILLISECONDS);
    private final TimeValue pollingCycleTimeInMillis = TimeValue.createValue(500L, TimeUnit.MILLISECONDS);

    public TimeValue getBaseWaitTime() {
        return baseWaitTimeInMillis;
    }

    public TimeValue getPageLoadWaitTime() {
        return pageLoadWaitTimeInMillis;
    }

    public TimeValue getPollingCycleTime() {
        return pollingCycleTimeInMillis;
    }
}
