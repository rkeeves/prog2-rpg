package hu.unideb.inf.ioc.cache;

public class CacheFactory {

    /**
     * Creates a cache based on the provided parameters
     * and returns it to the caller.
     * @param cacheStrategy enum deciding which cache to use
     * @param <T> type parameter of generic type cache
     * @return cache of type T
     */
    public <T> Cache<T> create(CacheStrategy cacheStrategy) {
        // IDE will cry about replacing switch,
        // but maybe in the future this will be expanded
        switch (cacheStrategy) {
            case SINGLETON:
                return new SingletonCache<>();
            default:
                return new NoCache<>();
        }
    }
}
