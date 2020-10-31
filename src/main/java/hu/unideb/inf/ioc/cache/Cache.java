package hu.unideb.inf.ioc.cache;

/**
 * A cache is responsible for storing an instance
 * of the parametrized type.
 * @param <T> type of cached instance
 */
public interface Cache<T> {

    /**
     * Check whether the cache contains an instance
     * @return true if cache has non null value
     */
    boolean hasCached();

    /**
     * Gets the cached reference which can be null
     * @return the currently cached reference
     */
    T getCached();

    /**
     * Sets the currently cached reference to a new instance
     * @param value new instance to be cached
     */
    void cache(T value);
}
