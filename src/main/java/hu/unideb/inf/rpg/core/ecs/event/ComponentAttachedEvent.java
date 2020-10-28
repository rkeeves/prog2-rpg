package hu.unideb.inf.rpg.core.ecs.component;

import lombok.Data;

@Data
public class ComponentAttachedEvent {

    private final Entity entity;

    private final Object component;
}
