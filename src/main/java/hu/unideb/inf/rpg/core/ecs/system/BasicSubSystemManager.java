package hu.unideb.inf.rpg.core.ecs.system;

import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BasicSubSystemManager implements SubSystemManager {

    private final List<SubSystem> subSystems;

    @Override
    public void setupListeners(EventManager events) {
        subSystems.forEach(s->s.onListenerSetup(events));
    }

    @Override
    public void beforeFirstUpdate(EventManager events, EntityManager entities) {
        subSystems.forEach(s->s.onBeforeFirstUpdate(events,entities));
    }

    @Override
    public void update(EventManager events, EntityManager entities) {
        subSystems.forEach(s->s.onUpdate(events,entities));
    }

    @Override
    public void shutdown(EventManager events, EntityManager entities) {
        subSystems.forEach(s->s.onShutdown(events,entities));
    }
}
