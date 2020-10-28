package hu.unideb.inf.rpg.core.ecs.component;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Collection of Components.
 * Entities must have a unique id.
 * Entities cannot be "deleted" by the clients,
 * instead they can be marked as deleted by the client,
 * so that EntityManager implementations can later clean them up.
 */
@Getter
public class Entity {

    private final Integer id;

    private boolean markedForDelete;

    private final Set<Class<?>> ownedComponentClassesCache = new HashSet<>();

    private final List<Object> components = new LinkedList<>();


    Entity(Integer id){
        this.id=id;
        this.markedForDelete=false;
    }

    public boolean hasComponents(Class<?>... componentClasses){
        Objects.requireNonNull(componentClasses);
        return Arrays.stream(componentClasses).allMatch(this::hasComponent);
    }

    private boolean hasComponent(Class<?> componentClass){
        Objects.requireNonNull(componentClass);
        return ownedComponentClassesCache.contains(componentClass);
    }

    void attachComponent(Object component){
        Objects.requireNonNull(component);
        components.add(component);
        ownedComponentClassesCache.add(component.getClass());
    }

    public <T> T getComponent(Class<T> componentClass){
        Objects.requireNonNull(componentClass);
        return components
                .stream()
                .filter(componentClass::isInstance)
                .findFirst()
                .map(componentClass::cast).orElseThrow();
    }

    public <T> List<T> getComponents(Class<T> componentClass){
        Objects.requireNonNull(componentClass);
        return components.stream()
                .filter(componentClass::isInstance)
                .map(componentClass::cast)
                .collect(Collectors.toList());
    }

    public boolean isMarkedForDelete(){
        return this.markedForDelete;
    }

    public void markForDelete(){
        this.markedForDelete = true;
    }
}
