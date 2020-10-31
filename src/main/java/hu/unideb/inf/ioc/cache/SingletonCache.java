package hu.unideb.inf.ioc.cache;

/**
 * This implementation can be set only the first time
 * @param <T>
 */
public class SingletonCache<T> implements Cache<T> {

    private T instance;

    @Override
    public boolean hasCached() {
        return instance != null;
    }

    @Override
    public T getCached() {
        return instance;
    }

    @Override
    public void cache(T value) {
        if (this.instance == null) {
            this.instance = value;
        }
    }
}
