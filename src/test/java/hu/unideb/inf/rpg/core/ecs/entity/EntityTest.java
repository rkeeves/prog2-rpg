package hu.unideb.inf.rpg.core.ecs.component;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    private static class A {

    }

    private static class B {

    }

    @Test
    void givenEntityWithoutCompTypeA_whenHasComponentsTypeA_ReturnFalse(){
        // given
        Entity e = new Entity(0);
        // when
        boolean res = e.hasComponents(A.class);
        // then
        assertFalse(res);
    }

    @Test
    void givenEntityWithCompTypeA_whenHasComponentsTypeA_ReturnTrue(){
        // given
        Entity e = new Entity(0);
        e.attachComponent(new A());
        // when
        boolean res = e.hasComponents(A.class);
        // then
        assertTrue(res);
    }

    @Test
    void givenEntityWithCompTypeA_whenHasComponentsTypeAB_ReturnTrue(){
        // given
        Entity e = new Entity(0);
        e.attachComponent(new A());
        // when
        boolean res = e.hasComponents(A.class,B.class);
        // then
        assertFalse(res);
    }

    @Test
    void givenEntityWithCompTypeAB_whenHasComponentsTypeAB_ReturnTrue(){
        // given
        Entity e = new Entity(0);
        e.attachComponent(new A());
        e.attachComponent(new B());
        // when
        boolean res = e.hasComponents(A.class,B.class);
        // then
        assertTrue(res);
    }

    @Test
    void givenEntityWithNoAComponent_whenGetComponentTypeA_ReturnEmpty(){
        // given
        Entity e = new Entity(0);
        // when
        var res = e.getComponent(A.class);
        // then
        assertEquals(Optional.empty(),res);
    }

    @Test
    void givenEntityWith1AComponent_whenGetComponentTypeA_ReturnInstance(){
        // given
        Entity e = new Entity(0);
        A a = new A();
        e.attachComponent(a);
        // when
        var res = e.getComponent(A.class);
        // then
        assertEquals(Optional.of(a),res);
    }

    @Test
    void givenEntityWith2AComponent_whenGetComponentTypeA_ReturnFirstAttachedInstance(){
        // given
        Entity e = new Entity(0);
        A a0 = new A();
        A a1 = new A();
        e.attachComponent(a0);
        e.attachComponent(a1);
        // when
        var res = e.getComponent(A.class);
        // then
        assertEquals(Optional.of(a0),res);
    }

    @Test
    void givenEntityWithNoAComponent_whenGetComponentsTypeA_ReturnEmptyList(){
        // given
        Entity e = new Entity(0);
        // when
        var res = e.getComponents(A.class);
        // then
        assertEquals(List.of(),res);
    }

    @Test
    void givenEntityWith2AComponent_whenGetComponentsTypeA_ReturnAll(){
        // given
        Entity e = new Entity(0);
        A a0 = new A();
        A a1 = new A();
        e.attachComponent(a0);
        e.attachComponent(a1);
        // when
        var res = e.getComponents(A.class);
        // then
        assertEquals(List.of(a0,a1),res);
    }
}
