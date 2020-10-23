package hu.unideb.inf.scene;

import java.util.Optional;

public interface SceneStackService {

    void pushScene(Scene scene);

    void popScene();

    Optional<Scene> getActiveScene();

    boolean hasActiveScene();
}
