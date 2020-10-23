package hu.unideb.inf.scene;

import hu.unideb.inf.io.IOService;
import lombok.Data;

@Data
public class HighScoreScene implements Scene{

    private final IOService ioService;

    private final JumpToMainMenuScene jumpToMainMenuScene;

    @Override
    public boolean onTurn(SceneStackService sceneStackService) {
        ioService.displayMessage("Your score is 0!");
        ioService.displayMessage("  Press any key to return to the main menu!");
        var resp = ioService.awaitResponseString();
        sceneStackService.popScene();
        sceneStackService.pushScene(jumpToMainMenuScene);
        return true;
    }
}
