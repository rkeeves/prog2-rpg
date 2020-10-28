package hu.unideb.inf.rpg.core.ecs.component;

import hu.unideb.inf.rpg.core.ecs.event.ComponentAttachedEvent;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Basic implementation for:
 * - entity creation
 * - entity filtered iteration
 * - entity delayed deletion
 *   (client marks entities for delete,
 *   actual removal from data structure is done later by an explicit call
 *   to clear method)
 */
@RequiredArgsConstructor
public class BasicEntityManager implements ClearableEntityManager {

    private Integer lastId = 0;

    private final List<Entity> entities = new LinkedList<>();

    private final EventManager eventManager;

    @Override
    public Entity create(){
        var e = new Entity(lastId++);
        entities.add(e);
        return e;
    }

    @Override
    public void forEach(Consumer<Entity> cons, Class<?>... componentClasses){
        entities
                .stream()
                .filter(e->this.isEntityAcceptableForConsumer(e,componentClasses))
                .forEach(cons);
    }

    @Override
    public void forFirst(Consumer<Entity> cons, Class<?>... componentClasses) {
        entities
                .stream()
                .filter(e->this.isEntityAcceptableForConsumer(e,componentClasses))
                .findFirst()
                .ifPresent(cons);
    }

    @Override
    public Optional<Entity> findFirst(Class<?>... componentClasses) {
        return entities
                .stream()
                .filter(e->this.isEntityAcceptableForConsumer(e,componentClasses))
                .findFirst();
    }

    @Override
    public <C> void attach(Entity entity, C component) {
        entity.attachComponent(component);
        var attachedEvent = new ComponentAttachedEvent(entity,component);
        eventManager.fire(attachedEvent);
    }

    private boolean isEntityAcceptableForConsumer(Entity e, Class<?>... componentClasses){
        return !e.isMarkedForDelete() && e.hasComponents(componentClasses);
    }

    @Override
    public void clearDead() {
        entities.removeIf(Entity::isMarkedForDelete);
    }
}
