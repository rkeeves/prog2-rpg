package hu.unideb.inf.ioc.provider;

import hu.unideb.inf.ioc.cache.Cache;
import hu.unideb.inf.ioc.cache.NoCache;
import hu.unideb.inf.ioc.cache.SingletonCache;
import hu.unideb.inf.ioc.container.Container;
import hu.unideb.inf.ioc.factory.ConstructorFactory;
import hu.unideb.inf.ioc.factory.Factory;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerAwareProviderTest {

    public static class MockContainer implements Container {

        @Override
        public <T> boolean hasProvider(Class<T> requiredProvidedType) {
            return false;
        }

        @Override
        public <T> Provider<T> getProvider(Class<T> requiredProvidedType) {
            return null;
        }

        @Override
        public <T> T getProvided(Class<T> requiredType) {
            return null;
        }
    }

    @Data
    public static class A{

    }

    Cache<A> aNoCache;

    Cache<A> aSingletonCache;

    Factory<A> aFactory;

    @Data
    public static class B{
        private final A a;
    }

    Cache<B> bNoCache;

    Cache<B> bSingletonCache;

    Factory<B> bFactory;

    @BeforeEach
    void init() throws NoSuchMethodException {
        aNoCache = new NoCache<>();
        aSingletonCache = new SingletonCache<>();
        aFactory = new ConstructorFactory<>(A.class,A.class.getConstructor());
        bNoCache = new NoCache<>();
        bSingletonCache = new SingletonCache<>();
        bFactory = new ConstructorFactory<>(B.class,B.class.getConstructor(A.class));
    }

    @Test
    void provide_NoCache_ReturnsNotTheSameInstanceButEqual(){
        // given
        ContainerAwareProvider<A> containerAwareProvider = new ContainerAwareProvider<>(A.class, aNoCache,aFactory,new MockContainer());
        // when
        var firstResult = containerAwareProvider.provide();
        var secondResult = containerAwareProvider.provide();
        // then
        assertNotSame(firstResult,secondResult);
        assertEquals(firstResult,secondResult);
    }

    @Test
    void provide_CacheDependenciesMissingFromContainer_ThrowsProviderInstanceCreationFailedWithDependencyNotConfiguredCause(){
        // given
        ContainerAwareProvider<B> containerAwareProvider = new ContainerAwareProvider<>(B.class, bSingletonCache,bFactory,new MockContainer());
        // when, then
        var exception = assertThrows(ProviderInstanceCreationFailedException.class, containerAwareProvider::provide);
        // then
        assertEquals(ProviderDependencyNotConfiguredException.class,exception.getCause().getClass());
    }
}
