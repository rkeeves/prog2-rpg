package hu.unideb.inf.ioc.provider;

import hu.unideb.inf.ioc.cache.Cache;
import hu.unideb.inf.ioc.container.Container;
import hu.unideb.inf.ioc.factory.Factory;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class ProviderFactory {

    public <T> Provider<T> createProvider(Class<T> providedClass,
                                          Factory<T> factory,
                                          Cache<T> cache,
                                          Container container) {
        return new ContainerAwareProvider<>(providedClass, cache, factory, container);
    }

    public <T> Provider<T> createProvider(Class<T> providedClass, Cache<T> cache, Supplier<T> supplier) {
        return new SupplierBasedProvider<>(providedClass, cache, supplier);
    }
}
