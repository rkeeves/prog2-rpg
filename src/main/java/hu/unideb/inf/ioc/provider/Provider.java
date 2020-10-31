package hu.unideb.inf.ioc.provider;

/**
 * A Provider provides instances for the client.
 * The Provider not necessarily creates new instances:
 * The Provider depending on the caching strategy can
 * return cached instances.
 * @param <T> type of provided instances
 */
public interface Provider<T> {

    /**
     * Returns the provided type.
     * @return class object of the provided type
     */
    Class<T> getProvidedClass();

    /**
     * Returns an instance which can be either new or cached,
     * depending on cache strategy.
     * @return instance
     */
    T provide();
}
