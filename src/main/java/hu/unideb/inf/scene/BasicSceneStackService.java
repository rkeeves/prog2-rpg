package hu.unideb.inf.scene;

import java.util.Optional;
import java.util.Stack;

public class BasicSceneStackService implements SceneStackService {

    private final Stack<Scene> sceneStack = new Stack<>();

    @Override
    public void pushScene(Scene scene) {
        getActiveScene().ifPresent(Scene::onDeactivate);
        sceneStack.push(scene);
        scene.onActivate();
    }

    @Override
    public void popScene() {
        if(!sceneStack.empty()){
            sceneStack.pop().onDeactivate();
        }
    }

    @Override
    public Optional<Scene> getActiveScene() {
        if(sceneStack.empty()){
            return Optional.empty();
        }
        return Optional.ofNullable(sceneStack.peek());
    }

    @Override
    public boolean hasActiveScene() {
        return !sceneStack.empty();
    }
}
