package hu.unideb.inf.ioc.provider;

import hu.unideb.inf.ioc.cache.Cache;
import hu.unideb.inf.ioc.container.Container;
import hu.unideb.inf.ioc.factory.Factory;

import java.util.Arrays;

public class ContainerAwareProvider<T> extends CachedProvider<T> {

    private final Factory<T> factory;

    private final Container container;

    public ContainerAwareProvider(Class<T> providedClass, Cache<T> cache, Factory<T> factory, Container container) {
        super(providedClass, cache);
        this.factory = factory;
        this.container = container;
    }

    private <C> Provider<C> mapTypeToProvider(Class<C> typeToProvide) {
        var provider = container.getProvider(typeToProvide);
        if (provider == null) {
            provider = new NullProvider<>(typeToProvide);
        }
        return provider;
    }

    @Override
    protected T construct() {
        var argsStream = Arrays.stream(factory.getParameterTypes())
                .map(this::mapTypeToProvider)
                .map(Provider::provide);
        return factory.newInstance(argsStream.toArray());
    }
}
