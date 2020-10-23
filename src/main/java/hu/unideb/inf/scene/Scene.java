package hu.unideb.inf.scene;

public interface Scene {

    default void onActivate() {

    }

    /**
     * This method gets called on each update.
     *
     * @param sceneStackService
     * @return true if the game loop should continue,
     *         false if the game loop should break immediately.
     */
    default boolean onTurn(SceneStackService sceneStackService){
        return false;
    }

    default void onDeactivate(){

    }
}
