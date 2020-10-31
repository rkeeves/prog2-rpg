package hu.unideb.inf.ioc.onthefly;

public class NoPublicCtorFoundException extends RuntimeException {
    public NoPublicCtorFoundException(String message) {
        super(message);
    }
}
