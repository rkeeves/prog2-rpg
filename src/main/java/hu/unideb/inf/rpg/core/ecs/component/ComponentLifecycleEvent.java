package hu.unideb.inf.rpg.core.ecs.component;

import lombok.Data;

@Data
public class ComponentLifecycleEvent {

    public enum ComponentPhase {
        POST_ATTACH
    }

    private final Entity entity;

    private final Object component;

    private final ComponentPhase componentPhase;
}
