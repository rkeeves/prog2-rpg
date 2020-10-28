package hu.unideb.inf.rpg.core.ecs.event;

import hu.unideb.inf.rpg.core.ecs.component.EntityManager;

@FunctionalInterface
public interface EventListener<E> {

    boolean accept(E event, EventManager events, EntityManager entities);
}