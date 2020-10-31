package hu.unideb.inf.ioc.container;

import hu.unideb.inf.ioc.provider.Provider;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class BaseContainer implements Container, ConfigurableContainer {

    private final Map<Class<?>, Provider<?>> providers = new ConcurrentHashMap<>();

    @Override
    public <T> void configureProvider(Class<T> typeToProvide, Provider<T> provider) {
        if (hasProvider(typeToProvide)) {
            throw new ProviderAlreadyConfiguredException("Provider for type " + typeToProvide + " was already registered");
        }
        Objects.requireNonNull(provider);
        providers.put(typeToProvide, provider);
    }

    @Override
    public <T> boolean hasProvider(Class<T> requiredProvidedType) {
        Objects.requireNonNull(requiredProvidedType);
        return providers.containsKey(requiredProvidedType);
    }

    @Override
    public <T> Provider<T> getProvider(Class<T> requiredProvidedType) {
        Objects.requireNonNull(requiredProvidedType);
        Provider<?> provider = providers.get(requiredProvidedType);
        return (Provider<T>) provider;
    }

    @Override
    public <T> T getProvided(Class<T> requiredProvidedType) {
        Provider<T> provider = getProvider(requiredProvidedType);
        if (provider == null) {
            throw new ProviderNotFoundException("No provider was configured for type " + requiredProvidedType);
        }
        return provider.provide();
    }
}
