package hu.unideb.inf.ioc.container;

import hu.unideb.inf.ioc.provider.Provider;

/**
 * A Container is responsible for managing Providers for different Types.
 * A Container is also used to get instances of Types from Providers.
 */
public interface Container {

    /**
     * Checks whether the Container holds a Provider for a given Type.
     * @param requiredProvidedType class object of given type
     * @param <T> type which the provider provides
     * @return true if Container holds a Provider
     */
    <T> boolean hasProvider(Class<T> requiredProvidedType);

    /**
     * Returns a Provider for a given type or null.
     * @param requiredProvidedType class object of given type
     * @param <T> type which the provider provides
     * @return provider for the given type or null
     */
    <T> Provider<T> getProvider(Class<T> requiredProvidedType);

    /**
     * Returns an instance from a Provider which was mapped to the Type.
     * <p>
     * Note: Can throw ProviderNotFoundException
     * </p>
     * @param requiredType class object of given type
     * @param <T> type which the provider provides
     * @return instance of type T
     */
    <T> T getProvided(Class<T> requiredType);
}
