package hu.unideb.inf.rpg.core.ecs.system;

import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;

public interface SubSystemManager {

    void setupListeners(EventManager events);

    void beforeFirstUpdate(EventManager events, EntityManager entities);

    void update(EventManager events, EntityManager entities);

    void shutdown(EventManager events, EntityManager entities);
}
