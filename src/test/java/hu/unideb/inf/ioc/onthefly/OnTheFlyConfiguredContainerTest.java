package hu.unideb.inf.ioc.onthefly;

import hu.unideb.inf.ioc.cache.CacheStrategy;
import hu.unideb.inf.ioc.container.BaseContainer;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

public class OnTheFlyConfiguredContainerTest {

    OnTheFlyConfiguredContainer onTheFlyConfiguredContainer;

    @BeforeEach
    void init() {
        onTheFlyConfiguredContainer = new OnTheFlyConfiguredContainer(new BaseContainer());
    }

    @Data
    public static class TypeWithNoPublicConstructor {

        private TypeWithNoPublicConstructor() {
        }
    }

    @Test
    void getCtorWithMaxArgCountOrThrow_ParamNull_ThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> onTheFlyConfiguredContainer.getCtorWithMaxArgCountOrThrow(null));
    }

    @Test
    void getCtorWithMaxArgCountOrThrow_ParamTypeHasNoPublicCtor_ThrowNoPublicCtorFoundException() {
        // given, when, then
        assertThrows(NoPublicCtorFoundException.class, () -> onTheFlyConfiguredContainer.getCtorWithMaxArgCountOrThrow(TypeWithNoPublicConstructor.class));
    }

    @Data
    public static class TypeWithTwoConstructors {
        private Integer a;

        // The IDE will tell you that ctor is never used,
        // but it is actually necessary for testing our reflection based methods
        public TypeWithTwoConstructors() {
        }

        // The IDE will tell you that ctor is never used,
        // but it is actually necessary for testing our reflection based methods
        public TypeWithTwoConstructors(Integer a) {
            this.a = a;
        }
    }

    @Test
    void getCtorWithMaxArgCountOrThrow_ParamTypeHasANumberOfConstructors_ReturnCtorWithMostParameters() throws NoSuchMethodException {
        // given
        Class<TypeWithTwoConstructors> expectedType = TypeWithTwoConstructors.class;
        Constructor<TypeWithTwoConstructors> expected = TypeWithTwoConstructors.class.getConstructor(Integer.class);
        // when
        Constructor<TypeWithTwoConstructors> result = onTheFlyConfiguredContainer.getCtorWithMaxArgCountOrThrow(expectedType);
        // then
        assertEquals(expected, result);
    }

    @Data
    public static class A {

    }

    @Test
    void getProvided_ParamTypeMaxArgCtorHasNoArgs_ReturnInstanceOfParamType() {
        // given
        Class<A> expectedType = A.class;
        // when, then
        assertNotNull(onTheFlyConfiguredContainer.getProvided(expectedType));
    }

    @Test
    void getProvided_ParamTypeMaxArgCtorHasNoArgs_ReturnSameInstanceAlways() {
        // given
        Class<A> expectedType = A.class;
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(expectedType);
        var secondResult = onTheFlyConfiguredContainer.getProvided(expectedType);
        // then
        assertSame(firstResult, secondResult);
    }

    @Data
    public static class B {

        private final A a;
    }

    @Test
    void getProvided_ParamTypeHasDependencyOnDefaultConstructable_ReturnSameInstanceAlways() {
        // given
        Class<B> expectedType = B.class;
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(expectedType);
        var secondResult = onTheFlyConfiguredContainer.getProvided(expectedType);
        // then
        assertSame(firstResult, secondResult);
    }

    @Data
    public static class C {

        private final B b;
    }

    @Test
    void getProvided_ParamTypeDependencyTreeAllNodesAreConstructable_ReturnSameInstanceAlways() {
        // given
        Class<C> expectedType = C.class;
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(expectedType);
        var secondResult = onTheFlyConfiguredContainer.getProvided(expectedType);
        // then
        assertSame(firstResult, secondResult);
    }

    @Data
    public static class TypeHasNonConstructableDependency {

        private final C c;

        private final TypeWithNoPublicConstructor nonConstructable;
    }

    @Test
    void getProvided_DependencyTreeAnyNodeIsNotConstructable_ThrowsOnTheFlyDependencyConfigurationWithCauseNoPublicCtorFound() {
        // given
        Class<TypeHasNonConstructableDependency> expectedType = TypeHasNonConstructableDependency.class;
        // when, then
        OnTheFlyDependencyConfigurationException exception = assertThrows(OnTheFlyDependencyConfigurationException.class, () -> onTheFlyConfiguredContainer.getProvided(expectedType));
        assertEquals(NoPublicCtorFoundException.class, exception.getCause().getClass());
    }

    @Data
    public static class TypeHasCyclicDependency {

        private final TypeHasCyclicDependencyOther a;
    }

    @Data
    public static class TypeHasCyclicDependencyOther {

        private final TypeHasCyclicDependency a;
    }

    @Test
    void getProvided_CyclicDependency_ThrowsOnTheFlyDependencyConfigurationWithCauseCyclicDependency() {
        // given
        Class<TypeHasCyclicDependency> expectedType = TypeHasCyclicDependency.class;
        // when, then
        OnTheFlyDependencyConfigurationException exception = assertThrows(OnTheFlyDependencyConfigurationException.class, () -> onTheFlyConfiguredContainer.getProvided(expectedType));
        var exceptionCause = exception.getCause();
        assertEquals(OnTheFlyDependencyConfigurationException.class, exceptionCause.getClass());
        var exceptionRootCause = exceptionCause.getCause();
        assertEquals(CyclicDependencyException.class, exceptionRootCause.getClass());
        assertNull(exceptionRootCause.getCause());
    }

    @Data
    public static class TypeHasDependencyOnSuppliedType {

        private final Integer a;
    }

    @Test
    void getProvided_TypeIsSupplierBasedNoCache_ReturnNotTheSameInstanceButEqual() {
        // given
        onTheFlyConfiguredContainer.configureSupplier(TypeHasDependencyOnSuppliedType.class, () -> new TypeHasDependencyOnSuppliedType(1), CacheStrategy.NO_CACHE);
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(TypeHasDependencyOnSuppliedType.class);
        var secondResult = onTheFlyConfiguredContainer.getProvided(TypeHasDependencyOnSuppliedType.class);
        // then
        assertNotSame(firstResult, secondResult);
        assertEquals(firstResult, secondResult);
    }

    @Test
    void getProvided_TypeIsSupplierBasedNoCache_ReturnTheSameInstance() {
        // given
        onTheFlyConfiguredContainer.configureSupplier(TypeHasDependencyOnSuppliedType.class, () -> new TypeHasDependencyOnSuppliedType(1), CacheStrategy.SINGLETON);
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(TypeHasDependencyOnSuppliedType.class);
        var secondResult = onTheFlyConfiguredContainer.getProvided(TypeHasDependencyOnSuppliedType.class);
        // then
        assertSame(firstResult, secondResult);
    }

    public interface InterfaceA {

    }

    @Data
    public static class ImplementationA implements InterfaceA {

        private final Integer value;
    }

    @Test
    void getProvided_TypeIsInterfaceAndSupplierBasedNoCache_ReturnNotTheSameInstanceButEqual() {
        // given
        onTheFlyConfiguredContainer.configureSupplier(InterfaceA.class, () -> new ImplementationA(1), CacheStrategy.NO_CACHE);
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(InterfaceA.class);
        var secondResult = onTheFlyConfiguredContainer.getProvided(InterfaceA.class);
        // then
        assertNotSame(firstResult, secondResult);
        assertEquals(firstResult, secondResult);
    }

    @Test
    void getProvided_TypeIsInterfaceAndSupplierBasedNoCache_ReturnTheSameInstance() {
        // given
        onTheFlyConfiguredContainer.configureSupplier(InterfaceA.class, () -> new ImplementationA(1), CacheStrategy.SINGLETON);
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(InterfaceA.class);
        var secondResult = onTheFlyConfiguredContainer.getProvided(InterfaceA.class);
        // then
        assertSame(firstResult, secondResult);
    }

    @Data
    public static class DependentOnInterfaceA {

        private final InterfaceA interfaceA;
    }

    @Data
    public static class DependentOnInterfaceAOther {

        private final InterfaceA interfaceA;
    }

    @Test
    void getProvided_DependencyNotCached_AlwaysReturnNewInstancesOfDependency() {
        // given
        onTheFlyConfiguredContainer.configureSupplier(InterfaceA.class, () -> new ImplementationA(1), CacheStrategy.NO_CACHE);
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(DependentOnInterfaceA.class);
        var secondResult = onTheFlyConfiguredContainer.getProvided(DependentOnInterfaceAOther.class);
        // then
        assertNotSame(firstResult.interfaceA, secondResult.interfaceA);
        assertEquals(firstResult.interfaceA, secondResult.interfaceA);
    }

    @Test
    void getProvided_DependencySingletonCached_AlwaysReturnSameInstancesOfDependency() {
        // given
        onTheFlyConfiguredContainer.configureSupplier(InterfaceA.class, () -> new ImplementationA(1), CacheStrategy.SINGLETON);
        // when
        var firstResult = onTheFlyConfiguredContainer.getProvided(DependentOnInterfaceA.class);
        var secondResult = onTheFlyConfiguredContainer.getProvided(DependentOnInterfaceAOther.class);
        // then
        assertSame(firstResult.interfaceA, secondResult.interfaceA);
        assertEquals(firstResult.interfaceA, secondResult.interfaceA);
    }
}
