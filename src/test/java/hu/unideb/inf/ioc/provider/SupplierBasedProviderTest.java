package hu.unideb.inf.ioc.provider;

import hu.unideb.inf.ioc.cache.Cache;
import hu.unideb.inf.ioc.cache.NoCache;
import hu.unideb.inf.ioc.cache.SingletonCache;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierBasedProviderTest {

    @Data
    public static class A {

        private final Integer a;
    }

    Cache<A> aNoCache;

    Cache<A> aSingletonCache;

    @BeforeEach
    void init() {
        aNoCache = new NoCache<>();
        aSingletonCache = new SingletonCache<>();
    }

    @Test
    void getProvidedClass_InstantiatedForTypeA_ReturnTypeA() {
        // given
        Class<A> expectedType = A.class;
        SupplierBasedProvider<A> supplierBasedProvider = new SupplierBasedProvider<>(expectedType, aNoCache, () -> new A(0));
        // when, then
        assertEquals(expectedType, supplierBasedProvider.getProvidedClass());
    }

    @Test
    void provide_InstantiatedForTypeAWithNoCache_ReturnNotTheSameInstanceButEqualAlways() {
        // given
        Class<A> expectedType = A.class;
        SupplierBasedProvider<A> supplierBasedProvider = new SupplierBasedProvider<>(expectedType, aNoCache, () -> new A(0));
        // when
        var firstResult = supplierBasedProvider.provide();
        var secondResult = supplierBasedProvider.provide();
        // then
        assertNotSame(firstResult, secondResult);
        assertEquals(firstResult, secondResult);
    }

    @Test
    void provide_InstantiatedForTypeAWithSingletonCache_ReturnSameInstanceAlways() {
        // given
        Class<A> expectedType = A.class;
        SupplierBasedProvider<A> supplierBasedProvider = new SupplierBasedProvider<>(expectedType, aSingletonCache, () -> new A(0));
        // when
        var firstResult = supplierBasedProvider.provide();
        var secondResult = supplierBasedProvider.provide();
        // then
        assertSame(firstResult, secondResult);
    }
}
