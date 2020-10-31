package hu.unideb.inf.ioc.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NoCacheTest {

    @Test
    void hasCached_CacheNotCalledPrior_ReturnsFalse(){
        // given
        NoCache<Integer> cache = new NoCache<>();
        // when
        var hasCached = cache.hasCached();
        // then
        assertFalse(hasCached);
    }

    @Test
    void hasCached_CacheWasCalledPrior_ReturnsFalse(){
        // given
        NoCache<Integer> cache = new NoCache<>();
        cache.cache(1);
        // when
        var hasCached = cache.hasCached();
        // then
        assertFalse(hasCached);
    }

    @Test
    void getCached_CacheWasNotCalledPrior_ReturnsNull(){
        // given
        NoCache<Integer> cache = new NoCache<>();
        // when
        var cached = cache.getCached();
        // then
        assertNull(cached);
    }

    @Test
    void getCached_CacheWasCalledPrior_ReturnsNull(){
        // given
        NoCache<Integer> cache = new NoCache<>();
        cache.cache(1);
        // when
        var cached = cache.getCached();
        // then
        assertNull(cached);
    }
}
