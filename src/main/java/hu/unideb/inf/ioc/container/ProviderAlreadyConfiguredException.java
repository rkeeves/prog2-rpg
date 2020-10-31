package hu.unideb.inf.ioc.container;

public class ProviderAlreadyConfiguredException extends RuntimeException {
    public ProviderAlreadyConfiguredException(String message) {
        super(message);
    }
}
