package hu.unideb.inf.scene;

import hu.unideb.inf.io.IOService;
import lombok.Data;

@Data
public class MainMenuScene implements Scene{

    private final IOService ioService;

    private final CharGenScene charGenScene;

    @Override
    public boolean onTurn(SceneStackService sceneStackService) {
        ioService.displayMessage("Welcome to the game!");
        ioService.displayMessage("  Press Q to exit!");
        ioService.displayMessage("  Press anything else to to start a new game!");
        var resp = ioService.awaitResponseString();
        if(resp.equalsIgnoreCase("Q")) {
            return false;
        }
        sceneStackService.popScene();
        sceneStackService.pushScene(charGenScene);
        return true;
    }
}
