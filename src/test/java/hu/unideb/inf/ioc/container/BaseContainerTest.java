package hu.unideb.inf.ioc.container;

import hu.unideb.inf.ioc.cache.CacheFactory;
import hu.unideb.inf.ioc.cache.CacheStrategy;
import hu.unideb.inf.ioc.factory.FactoryFactory;
import hu.unideb.inf.ioc.provider.Provider;
import hu.unideb.inf.ioc.provider.ProviderFactory;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BaseContainerTest {

    private final CacheFactory cacheFactory = new CacheFactory();

    private final FactoryFactory factoryFactory = new FactoryFactory();

    private final ProviderFactory providerFactory = new ProviderFactory();

    @Data
    public static class A{

    }

    private Provider<A> getProviderA(Container container, CacheStrategy cacheStrategy) throws NoSuchMethodException {
        return providerFactory.createProvider(
                A.class,
                factoryFactory.create(
                        A.class,
                        A.class.getConstructor()),
                cacheFactory.create(cacheStrategy),
                container);
    }

    @Test
    void hasProvider_ProviderNotConfiguredPrior_ReturnFalse(){
        // given
        BaseContainer baseContainer = new BaseContainer();
        // when, then
        assertFalse(baseContainer.hasProvider(A.class));
    }

    @Test
    void hasProvider_ProviderConfiguredPrior_ReturnTrue() throws NoSuchMethodException {
        // given
        BaseContainer baseContainer = new BaseContainer();
        baseContainer.configureProvider(A.class,getProviderA(baseContainer, CacheStrategy.SINGLETON));
        // when, then
        assertTrue(baseContainer.hasProvider(A.class));
    }

    @Test
    void getProvider_ProviderNotConfiguredPrior_ReturnNull(){
        // given
        BaseContainer baseContainer = new BaseContainer();
        // when, then
        assertNull(baseContainer.getProvider(A.class));
    }

    @Test
    void getProvider_ProviderConfiguredPrior_ReturnNonNullProvider() throws NoSuchMethodException {
        // given
        BaseContainer baseContainer = new BaseContainer();
        baseContainer.configureProvider(A.class,getProviderA(baseContainer, CacheStrategy.SINGLETON));
        // when, then
        assertNotNull(baseContainer.getProvider(A.class));
    }

    @Test
    void getProvider_ProviderConfiguredPrior_ReturnSameInstance() throws NoSuchMethodException {
        // given
        BaseContainer baseContainer = new BaseContainer();
        baseContainer.configureProvider(A.class,getProviderA(baseContainer, CacheStrategy.SINGLETON));
        // when, then
        var firstResult = baseContainer.getProvider(A.class);
        var secondResult = baseContainer.getProvider(A.class);
        // then
        assertSame(firstResult, secondResult);
    }

    @Test
    void getProvided_ProviderNotConfiguredPrior_ThrowProviderNotFoundException() {
        // given
        BaseContainer baseContainer = new BaseContainer();
        // when, then
        assertThrows(ProviderNotFoundException.class, ()-> baseContainer.getProvided(A.class));
    }

    @Test
    void getProvided_ProviderConfiguredWithSingletonCachePrior_ReturnNonNullInstance() throws NoSuchMethodException {
        // given
        BaseContainer baseContainer = new BaseContainer();
        baseContainer.configureProvider(A.class,getProviderA(baseContainer,CacheStrategy.SINGLETON));
        // when, then
        assertNotNull(baseContainer.getProvided(A.class));
    }

    @Test
    void getProvided_ProviderConfiguredWithNoCachePrior_ReturnNonNullInstance() throws NoSuchMethodException {
        // given
        BaseContainer baseContainer = new BaseContainer();
        baseContainer.configureProvider(A.class,getProviderA(baseContainer,CacheStrategy.NO_CACHE));
        // when, then
        assertNotNull(baseContainer.getProvided(A.class));
    }

    @Test
    void getProvided_ProviderConfiguredWithSingletonPrior_ReturnInstancesTheSameButEquals() throws NoSuchMethodException {
        // given
        BaseContainer baseContainer = new BaseContainer();
        baseContainer.configureProvider(A.class,getProviderA(baseContainer,CacheStrategy.SINGLETON));
        // when, then
        var firstResult = baseContainer.getProvided(A.class);
        var secondResult = baseContainer.getProvided(A.class);
        // then
        assertSame(firstResult, secondResult);
    }

    @Test
    void getProvided_ProviderConfiguredWithNoCachePrior_ReturnInstancesNotTheSameButEquals() throws NoSuchMethodException {
        // given
        BaseContainer baseContainer = new BaseContainer();
        baseContainer.configureProvider(A.class,getProviderA(baseContainer,CacheStrategy.NO_CACHE));
        // when, then
        var firstResult = baseContainer.getProvided(A.class);
        var secondResult = baseContainer.getProvided(A.class);
        // then
        assertNotSame(firstResult , secondResult);
        assertEquals(firstResult, secondResult);
    }
}
