package hu.unideb.inf.ioc.provider;

import hu.unideb.inf.ioc.cache.Cache;

public abstract class CachedProvider<T> extends BaseProvider<T>{

    private final Cache<T> cache;

    public CachedProvider(Class<T> providedClass, Cache<T> cache) {
        super(providedClass);
        this.cache=cache;
    }

    @Override
    public final T provide() {
        if (cache.hasCached()) {
            return cache.getCached();
        } else {
            try {
                var instance = construct();
                cache.cache(instance);
                return instance;
            } catch (Exception e) {
                throw new ProviderInstanceCreationFailedException("Instantiation failed for type " + getProvidedClass(), e);
            }
        }
    }

    protected abstract T construct();
}
