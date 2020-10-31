package hu.unideb.inf.ioc.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseProvider<T> implements Provider<T> {

    @Getter
    private final Class<T> providedClass;
}
