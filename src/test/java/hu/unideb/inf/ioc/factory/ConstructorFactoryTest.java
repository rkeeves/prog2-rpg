package hu.unideb.inf.ioc.factory;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorFactoryTest {

    @Data
    static class A{

    }

    @Test
    public void getReturnType_InstantiatedWithTypeA_ReturnsTypeA() throws NoSuchMethodException {
        // given
        Class<A> expectedType = A.class;
        Constructor<A> ctor = expectedType.getConstructor();
        var constructorFactory = new ConstructorFactory<>(expectedType,ctor);
        // when
        var productClass = constructorFactory.getReturnType();
        // then
        assertEquals(expectedType,productClass);
    }

    @Test
    public void newInstance_UsingTypeA_ReturnsInstanceOfTypeA() throws NoSuchMethodException {
        // given
        Class<A> expectedType = A.class;
        var constructorFactory = new ConstructorFactory<>(expectedType,expectedType.getConstructor());
        // when
        var result = constructorFactory.newInstance();
        // then
        assertEquals(expectedType,result.getClass());
    }

    @Test
    public void newInstance_UsingTypeA_ReturnedInstancesAreNotTheSameButEqual() throws NoSuchMethodException {
        // given
        Class<A> expectedProductClass = A.class;
        var constructorFactory = new ConstructorFactory<>(expectedProductClass,expectedProductClass.getConstructor());
        // when
        var firstResult = constructorFactory.newInstance();
        var secondResult = constructorFactory.newInstance();
        // then
        assertNotSame(firstResult , secondResult);
        assertEquals(firstResult , secondResult);
    }

    @Test
    public void newInstance_UsingTypeAParamsInvalidForCtor_ThrowsFactoryInstanceCreationException() throws NoSuchMethodException {
        // given
        Class<A> expectedProductClass = A.class;
        var constructorFactory = new ConstructorFactory<>(expectedProductClass,expectedProductClass.getConstructor());
        // when, then
        assertThrows(FactoryInstanceCreationException.class,()->constructorFactory.newInstance(1));
    }
}
