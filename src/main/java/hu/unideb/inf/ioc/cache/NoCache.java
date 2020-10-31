package hu.unideb.inf.ioc.cache;

/**
 * This implementation never caches
 * @param <T> type of cached instance
 */
public class NoCache<T> implements Cache<T> {

    @Override
    public boolean hasCached() {
        return false;
    }

    @Override
    public T getCached() {
        return null;
    }

    @Override
    public void cache(T value) {
    }
}
