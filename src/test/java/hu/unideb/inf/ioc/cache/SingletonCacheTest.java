package hu.unideb.inf.ioc.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonCacheTest {

    public static class A{}

    @Test
    void hasCached_CacheNotCalledPrior_ReturnsFalse(){
        // given
        SingletonCache<A> cache = new SingletonCache<>();
        // when
        var hasCached = cache.hasCached();
        // then
        assertFalse(hasCached);
    }

    @Test
    void hasCached_CacheWasCalledPrior_ReturnsTrue(){
        // given
        SingletonCache<A> cache = new SingletonCache<>();
        cache.cache(new A());
        // when
        var hasCached = cache.hasCached();
        // then
        assertTrue(hasCached);
    }

    @Test
    void getCached_CacheWasNotCalledPrior_ReturnsNull(){
        // given
        SingletonCache<A> cache = new SingletonCache<>();
        // when
        var cached = cache.getCached();
        // then
        assertNull(cached);
    }

    @Test
    void getCached_CacheWasCalledPriorWithObject_ReturnsTheCachedInstance(){
        // given
        A expected = new A();
        SingletonCache<A> cache = new SingletonCache<>();
        cache.cache(expected);
        // when
        var cached = cache.getCached();
        // then
        assertSame(expected,cached);
        assertEquals(expected,cached);
    }
}
