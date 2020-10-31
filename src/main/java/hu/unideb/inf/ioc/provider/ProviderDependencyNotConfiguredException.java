package hu.unideb.inf.ioc.provider;

public class ProviderDependencyNotConfiguredException extends RuntimeException {
    public ProviderDependencyNotConfiguredException(String message) {
        super(message);
    }
}
