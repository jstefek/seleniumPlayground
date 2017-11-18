package org.jstefek.seleniumPlayground.browser.enhancer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;
import org.jstefek.seleniumPlayground.pages.utils.ReflectionUtilsService;
import org.mockito.Mockito;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverEventListenersProviderTest {

    private WebDriverEventListener mockedListener;
    private WebDriverEventListenersProvider provider;
    private ReflectionUtilsService service;
    private Set<Class<? extends WebDriverEventListener>> set;

    @BeforeMethod
    public void setup() {
        mockedListener = Mockito.mock(WebDriverEventListener.class);
        set = Mockito.mock(Set.class);
        service = Mockito.mock(ReflectionUtilsService.class);
        when(service.getAllSubTypesWithAnnotation(WebDriverEventListener.class, WDEventListener.class))
                .thenReturn(set);
        provider = new WebDriverEventListenersProvider(service);
    }

    @Test
    public void testGet_serviceReturnsEmptySet_emptyArrayIsReturned() {
        when(set.isEmpty()).thenReturn(true);
        Assert.assertTrue(provider.get().length == 0);
    }

    @Test
    public void testGet_serviceReturnsSet_arrayIsReturned() {
        when(set.isEmpty()).thenReturn(false);
        when(set.size()).thenReturn(1);
        when(set.iterator()).thenReturn(new Iterator<Class<? extends WebDriverEventListener>>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i == 0;
            }

            @Override
            public Class<? extends WebDriverEventListener> next() {
                i++;
                return WebDriverEventListener.class;
            }
        });
        when(service.instanciate(any())).thenReturn(mockedListener);

        WebDriverEventListener[] get = provider.get();
        assertEquals(get.length, 1);
        assertEquals(get[0], mockedListener);
    }
}
