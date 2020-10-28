package hu.unideb.inf.rpg.player;

import hu.unideb.inf.rpg.core.ecs.entity.Entity;
import lombok.Data;

@Data
public class PlayerSpawnedEvent {

    private final Entity playerEntity;
}
