package org.jstefek.seleniumPlayground.config;

import java.util.concurrent.TimeUnit;

/**
 * Wrapper for manipulating time values
 */
public class TimeValue {

    /**
     * @param defaultValue default value
     * @param tu default time unit
     * @return new TimeValue instance with given properties
     */
    public static TimeValue createValue(long defaultValue, TimeUnit tu) {
        return new TimeValue(defaultValue, tu);
    }

    private long actualValue;
    private final long defaultValue;
    private final TimeUnit timeUnit;

    /**
     * @param defaultValue default value of this TimeValue
     * @param timeUnit default time unit of this instance, this value cannot be modified later (use the smallest)
     */
    public TimeValue(long defaultValue, TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new IllegalArgumentException("Time unit cannot be null!");

        }
        this.defaultValue = defaultValue;
        this.actualValue = defaultValue;
        this.timeUnit = timeUnit;
    }

    /**
     * @param u unit to which will be the actual value converted
     * @return converted value to given time unit
     */
    public long getValue(TimeUnit u) {
        return u.convert(this.actualValue, timeUnit);
    }

    /**
     * resets the actual value to the default value
     */
    public void resetValue() {
        actualValue = defaultValue;
    }

    /**
     * @param value time value to be stored
     * @param to time unit of the given value
     */
    public void setValue(long value, TimeUnit to) {
        actualValue = timeUnit.convert(value, to);
    }

}
