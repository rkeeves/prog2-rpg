package hu.unideb.inf.scene;

import hu.unideb.inf.io.IOService;
import lombok.Data;

@Data
public class WorldGenScene implements Scene{

    private final IOService ioService;

    private final PlayScene playScene;

    private final JumpToMainMenuScene jumpToMainMenuScene;

    @Override
    public boolean onTurn(SceneStackService sceneStackService) {
        ioService.displayMessage("Starting game!");
        ioService.displayMessage("  You can press Q to go back to the main menu!");
        var resp = ioService.awaitResponseString();
        if(resp.equalsIgnoreCase("Q")){
            sceneStackService.popScene();
            sceneStackService.pushScene(jumpToMainMenuScene);
        }else {
            sceneStackService.popScene();
            sceneStackService.pushScene(playScene);
        }
        return true;
    }
}
