package hu.unideb.inf.ioc.factory;

public abstract class BaseFactory<T> implements Factory<T> {

    private final Class<T> productClass;

    protected BaseFactory(Class<T> productClass) {
        this.productClass = productClass;
    }

    @Override
    public Class<T> getReturnType() {
        return this.productClass;
    }
}
