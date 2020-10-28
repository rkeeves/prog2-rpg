package hu.unideb.inf.rpg.core.ecs;

import hu.unideb.inf.rpg.core.ecs.entity.BasicEntityManager;
import hu.unideb.inf.rpg.core.ecs.entity.ClearableEntityManager;
import hu.unideb.inf.rpg.core.ecs.event.BasicEventManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import hu.unideb.inf.rpg.core.ecs.system.BasicSubSystemManager;
import hu.unideb.inf.rpg.core.ecs.system.SubSystem;
import hu.unideb.inf.rpg.core.ecs.system.SubSystemManager;

import java.util.List;

public class ECS {

    private final EventManager events = new BasicEventManager();

    private final ClearableEntityManager entities = new BasicEntityManager(events);

    private final SubSystemManager systems;

    public ECS(List<SubSystem> systems){
        this.systems = new BasicSubSystemManager(systems);
    }

    public void configure(){
        events.setEntityManager(entities);
        systems.setupListeners(events);
    }

    public void beforeFirstUpdate(){
        systems.beforeFirstUpdate(events,entities);
    }

    public void update(){
        systems.update(events,entities);
        entities.clearDead();
    }

    public void shutdown(){
        systems.shutdown(events,entities);
        entities.clearDead();
    }
}
