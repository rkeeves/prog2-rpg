package hu.unideb.inf.ioc.onthefly;

public class CyclicDependencyException extends RuntimeException {
    public CyclicDependencyException(String message) {
        super(message);
    }
}
