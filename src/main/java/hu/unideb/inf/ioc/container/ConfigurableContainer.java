package hu.unideb.inf.ioc.container;

import hu.unideb.inf.ioc.provider.Provider;

/**
 * This interface is used for configuring Providers in a Container.
 */
public interface ConfigurableContainer extends Container {

    /**
     * Configures a Provider for a given type.
     * <p>
     * Note: Two Providers cannot be mapped to the same class object.
     * The second call can throw a ProviderAlreadyConfiguredException.
     * </p>
     *
     * @param typeToProvide class object of the type that the Provider provides
     * @param provider the Provider to be configured
     * @param <T> type that the Provider provides
     */
    <T> void configureProvider(Class<T> typeToProvide, Provider<T> provider);
}
