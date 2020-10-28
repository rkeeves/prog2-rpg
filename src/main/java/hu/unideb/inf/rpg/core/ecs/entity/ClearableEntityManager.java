package hu.unideb.inf.rpg.core.ecs.entity;

/**
 * This interface provides a way to clean up dead entities.
 * This interface is hidden from clients who only want to
 * iterate and create entities.
 * This is necessary, because we don't want clients to directly
 * delete from the underlying data structure.
 */
public interface ClearableEntityManager extends EntityManager {

    /**
     * Clears all entities which are marked as delete from the underlying data structure.
     */
    void clearDead();
}
