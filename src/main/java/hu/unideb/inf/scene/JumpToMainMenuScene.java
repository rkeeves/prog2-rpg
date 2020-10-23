package hu.unideb.inf.scene;

import hu.unideb.inf.io.IOService;
import lombok.Data;

@Data
public class JumpToMainMenuScene implements Scene {

    private final IOService ioService;

    private MainMenuScene mainMenuScene;

    @Override
    public boolean onTurn(SceneStackService sceneStackService) {
        sceneStackService.popScene();
        sceneStackService.pushScene(mainMenuScene);
        return true;
    }
}
