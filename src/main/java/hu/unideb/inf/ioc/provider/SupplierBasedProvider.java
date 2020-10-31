package hu.unideb.inf.ioc.provider;

import hu.unideb.inf.ioc.cache.Cache;

import java.util.function.Supplier;

public class SupplierBasedProvider<T> extends CachedProvider<T>{

    private final Supplier<T> supplier;

    public SupplierBasedProvider(Class<T> providedClass, Cache<T> cache, Supplier<T> supplier) {
        super(providedClass, cache);
        this.supplier = supplier;
    }

    @Override
    protected T construct() {
        return supplier.get();
    }
}
