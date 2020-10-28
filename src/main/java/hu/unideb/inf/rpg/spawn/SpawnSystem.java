package hu.unideb.inf.rpg.spawn;

import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import hu.unideb.inf.rpg.core.ecs.system.SubSystem;
import hu.unideb.inf.rpg.player.PlayerComponent;
import hu.unideb.inf.rpg.player.PlayerSpawnedEvent;

public class SpawnSystem implements SubSystem {
    @Override
    public void onBeforeFirstUpdate(EventManager events, EntityManager entities) {
        var playerEntity = entities.create();
        entities.attach(playerEntity, new PlayerComponent());
        events.fire(new PlayerSpawnedEvent(playerEntity));
    }
}
