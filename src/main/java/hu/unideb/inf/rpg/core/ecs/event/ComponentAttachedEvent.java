package hu.unideb.inf.rpg.core.ecs.event;

import hu.unideb.inf.rpg.core.ecs.entity.Entity;
import lombok.Data;

@Data
public class ComponentAttachedEvent {

    private final Entity entity;

    private final Object component;
}
