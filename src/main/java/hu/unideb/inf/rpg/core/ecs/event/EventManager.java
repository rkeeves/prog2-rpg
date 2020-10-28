package hu.unideb.inf.rpg.core.ecs.event;

import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;

public interface EventManager {

    void setEntityManager(EntityManager em);
    <E> void register(Class<E> eventClass, EventListener<E> consumer);

    <E> void fire(E event);
}
