package hu.unideb.inf.ioc.onthefly;

import hu.unideb.inf.ioc.cache.Cache;
import hu.unideb.inf.ioc.cache.CacheFactory;
import hu.unideb.inf.ioc.cache.CacheStrategy;
import hu.unideb.inf.ioc.container.ConfigurableContainer;
import hu.unideb.inf.ioc.container.Container;
import hu.unideb.inf.ioc.factory.Factory;
import hu.unideb.inf.ioc.factory.FactoryFactory;
import hu.unideb.inf.ioc.provider.Provider;
import hu.unideb.inf.ioc.provider.ProviderFactory;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class OnTheFlyConfiguredContainer implements Container {

    private final CacheFactory cacheFactory = new CacheFactory();

    private final FactoryFactory factoryFactory = new FactoryFactory();

    private final ProviderFactory providerFactory = new ProviderFactory();

    private final ConfigurableContainer configurableContainer;

    public <T> void configureSupplier(Class<T> providedType, Supplier<T> supplier, CacheStrategy cacheStrategy) {
        Cache<T> cache = cacheFactory.create(cacheStrategy);
        var provider = providerFactory.createProvider(providedType, cache,supplier);
        configurableContainer.configureProvider(providedType, provider);
    }

    @Override
    public <T> boolean hasProvider(Class<T> requiredProvidedType) {
        return configurableContainer.hasProvider(requiredProvidedType);
    }

    @Override
    public <T> Provider<T> getProvider(Class<T> requiredProvidedType) {
        return configurableContainer.getProvider(requiredProvidedType);
    }

    @Override
    public <T> T getProvided(Class<T> requiredType) {
        if (!hasProvider(requiredType)) {
            autoConfigureProvider(new HashSet<>(), requiredType);
        }
        return getProvider(requiredType).provide();
    }

    private <T> void autoConfigureProvider(Set<Class<?>> typesCurrentlyUnderAutoConfig, Class<T> requiredType) {
        if (typesCurrentlyUnderAutoConfig.contains(requiredType)) {
            throw new CyclicDependencyException("Cyclic dependency was found for class " + requiredType);
        }
        typesCurrentlyUnderAutoConfig.add(requiredType);
        Constructor<T> constructor = getCtorWithMaxArgCountOrThrow(requiredType);
        Factory<T> factory = factoryFactory.create(requiredType, constructor);
        Cache<T> cache = cacheFactory.create(CacheStrategy.SINGLETON);
        try {
            Arrays.stream(factory.getParameterTypes())
                    .filter(paramType -> !this.hasProvider(paramType))
                    .forEach(provider -> this.autoConfigureProvider(typesCurrentlyUnderAutoConfig, provider));
        } catch (Exception e) {
            throw new OnTheFlyDependencyConfigurationException("Exception for type " + requiredType + " during auto configuration", e);
        }
        var provider = providerFactory.createProvider(requiredType, factory, cache, this);
        configurableContainer.configureProvider(requiredType, provider);
        typesCurrentlyUnderAutoConfig.remove(requiredType);

    }

    public <T> Constructor<T> getCtorWithMaxArgCountOrThrow(Class<T> type) {
        Objects.requireNonNull(type);
        return Arrays.stream(type.getConstructors())
                .max(Comparator.comparingInt(a -> a.getParameterTypes().length))
                .map(ctor -> (Constructor<T>) ctor)
                .orElseThrow(() -> new NoPublicCtorFoundException("No constructor was found for type " + type));
    }
}
