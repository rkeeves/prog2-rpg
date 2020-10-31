package hu.unideb.inf.ioc.factory;

import java.lang.reflect.Constructor;

public class FactoryFactory {

    public <T> Factory<T> create(Class<T> type, Constructor<T> constructor) {
        return new ConstructorFactory<>(type, constructor);
    }
}
