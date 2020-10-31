package hu.unideb.inf.ioc.provider;

public class NullProvider<T> extends BaseProvider<T> {

    public NullProvider(Class<T> providedClass) {
        super(providedClass);
    }

    @Override
    public T provide() {
        throw new ProviderDependencyNotConfiguredException("No provider was available for " + getProvidedClass());
    }
}
