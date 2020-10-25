package hu.unideb.inf.rpg.core.ecs.component;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * This granular interface assigns the implementor
 * the ability to iterate through entities.
 *
 * This granular interface assigns the implementor
 * the ability to create an Entity.
 * Id generation and other administrative work
 * is the implementor's responsibility/choice.
 */
public interface EntityManager {

    /**
     * Creates a new entity
     * @return newly created entity
     */
    Entity create();

    /**
     * Visits all entities for which both predicates are true:
     * - is NOT marked for deletion
     * - have at least 1 component of all the given types
     *
     * @param cons Consumer to accept a given entity
     * @param componentClasses Types of components which must be present on each visited entity
     */
    void forEach(Consumer<Entity> cons, Class<?>... componentClasses);

    /**
     * Visits maximum 1 entity for which both predicates are true:
     * - is NOT marked for deletion
     * - have at least 1 component of all the given types
     *
     * @param cons Consumer to accept a given entity
     * @param componentClasses Types of components which must be present on each visited entity
     */
    void forFirst(Consumer<Entity> cons, Class<?>... componentClasses);

    /**
     * Finds the first matching entity for which both predicates are true:
     * - is NOT marked for deletion
     * - have at least 1 component of all the given types
     * @param componentClasses
     * @return
     */
    Optional<Entity> findFirst(Class<?>... componentClasses);

    /**
     * Attaches to the given entity the given component,
     * and generates life cycle events.
     * @param entity entity to attach to
     * @param component component to attach
     * @param <C> component type
     */
    <C> void attach(Entity entity, C component);
}
