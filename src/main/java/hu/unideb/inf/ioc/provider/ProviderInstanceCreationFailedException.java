package hu.unideb.inf.ioc.provider;

public class ProviderInstanceCreationFailedException extends RuntimeException {
    public ProviderInstanceCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
