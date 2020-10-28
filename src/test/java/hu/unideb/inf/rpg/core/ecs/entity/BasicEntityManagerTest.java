package hu.unideb.inf.rpg.core.ecs.entity;

import hu.unideb.inf.rpg.core.ecs.event.EventListener;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicEntityManagerTest {

    private final EventManager eventsMock = new EventManager() {

        @Override
        public void setEntityManager(EntityManager em) {

        }

        @Override
        public <E> void register(Class<E> eventClass, EventListener<E> consumer) {

        }

        @Override
        public <E> void fire(E event) {

        }
    };

    private BasicEntityManager em;

    private static int counter;

    @BeforeEach
    void initManager() {
        counter = 0;
        em = new BasicEntityManager(eventsMock);
    }

    private static class A {
        public A(){}
    }

    private static class B {
        public B(){}
    }

    void createNEntitiesWithComponents(int n, Supplier<?>... suppliers) {
        for (int i = 0; i<n; i++){
            var e = em.create();
            for (var s : suppliers) {
                e.attachComponent(s.get());
            }
        }
    }

    @Test
    void givenNoEntitiesWithComponentA_whenForEachWithComponentA_VisitCount0(){
        // given
        // when
        em.forEach(e->counter++,A.class);
        // then
        assertEquals(0,counter);
    }

    @Test
    void givenEntities1A_whenForEachWithComponentA_VisitCount1(){
        // given
        int expectedVisitCount = 1;
        createNEntitiesWithComponents(expectedVisitCount, A::new);
        // when
        em.forEach(e->counter++,A.class);
        // then
        assertEquals(expectedVisitCount,counter);
    }

    @Test
    void givenEntities10A_whenForEachWithComponentA_VisitCount10(){
        // given
        int expectedVisitCount = 10;
        createNEntitiesWithComponents(expectedVisitCount,A::new);
        // when
        em.forEach(e->counter++,A.class);
        // then
        assertEquals(expectedVisitCount,counter);
    }

    @Test
    void givenEntities10A10B10AB_whenForEachWithComponentA_VisitCount20(){
        // given
        int aCount = 10;
        int bCount = 10;
        int abCount = 10;
        createNEntitiesWithComponents(aCount, A::new);
        createNEntitiesWithComponents(bCount,B::new);
        createNEntitiesWithComponents(abCount,A::new,B::new);
        // when
        em.forEach(e->counter++,A.class);
        // then
        assertEquals(aCount+abCount,counter);
    }
}
