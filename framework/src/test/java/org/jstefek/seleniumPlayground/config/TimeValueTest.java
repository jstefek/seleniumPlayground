package org.jstefek.seleniumPlayground.config;

import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TimeValueTest {

    @DataProvider(name = "getValueData")
    public static Object[][] data() {
        return new Object[][]{
            {1, TimeUnit.MINUTES, TimeUnit.MINUTES, 1},
            {1, TimeUnit.MINUTES, TimeUnit.SECONDS, 1 * 60},
            {1, TimeUnit.MINUTES, TimeUnit.MILLISECONDS, 1 * 60 * 1000},
            {60, TimeUnit.MINUTES, TimeUnit.HOURS, 1}
        };
    }

    @DataProvider(name = "setValueData")
    public static Object[][] timeUnitsData() {
        return new Object[][]{
            {TimeUnit.DAYS},
            {TimeUnit.HOURS},
            {TimeUnit.MINUTES},
            {TimeUnit.SECONDS},
            {TimeUnit.MILLISECONDS},
            {TimeUnit.MICROSECONDS},
            {TimeUnit.NANOSECONDS}
        };
    }

    @Test(dataProvider = "getValueData")
    public void testGetValue(long value, TimeUnit from, TimeUnit to, long expectedValue) {
        TimeValue tv = new TimeValue(value, from);
        Assert.assertEquals(tv.getValue(to), expectedValue);
    }

    @Test
    public void testResetValue() {
        final long defMinutes = 1;
        TimeValue t = new TimeValue(defMinutes, TimeUnit.MINUTES);
        t.setValue(10, TimeUnit.DAYS);
        t.resetValue();
        Assert.assertEquals(t.getValue(TimeUnit.MINUTES), defMinutes);
    }

    @Test(dataProvider = "setValueData")
    public void testSetValue(TimeUnit tu) {
        TimeValue t = new TimeValue(1, TimeUnit.NANOSECONDS);
        t.setValue(2, tu);
        Assert.assertEquals(t.getValue(tu), 2, String.format("Used TimeUnit <%s>.", tu));
    }

}
