package hu.unideb.inf.rpg.core.ecs.system;

import hu.unideb.inf.rpg.core.ecs.component.EntityManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;

public interface SubSystem {

    default void onListenerSetup(EventManager events) {

    }

    default void onUpdate(EventManager events, EntityManager entities) {

    }

    default void onShutdown(EventManager events, EntityManager entities){

    }
}
