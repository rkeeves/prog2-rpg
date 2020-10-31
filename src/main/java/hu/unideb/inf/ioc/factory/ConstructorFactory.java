package hu.unideb.inf.ioc.factory;

import java.lang.reflect.Constructor;

public class ConstructorFactory<T> extends BaseFactory<T> {

    private final Constructor<?> constructor;

    public ConstructorFactory(Class<T> productClass, Constructor<T> constructor) {
        super(productClass);
        this.constructor = constructor;
    }

    @Override
    public T newInstance(Object... args) {
        try {
            return this.getReturnType().cast(constructor.newInstance(args));
        } catch (Exception e) {
            throw new FactoryInstanceCreationException(e);
        }
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return constructor.getParameterTypes();
    }
}
