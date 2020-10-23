package hu.unideb.inf.scene;

import hu.unideb.inf.factory.PlayerCreationService;
import hu.unideb.inf.io.IOService;
import lombok.Data;

@Data
public class CharGenScene implements Scene {

    private final IOService ioService;

    private final WorldGenScene worldGenScene;

    private final JumpToMainMenuScene jumpToMainMenuScene;

    private final PlayerCreationService playerCreationService;
    @Override
    public boolean onTurn(SceneStackService sceneStackService) {
        ioService.displayMessage("Please enter the name of your character!");
        ioService.displayMessage("  You can type exit to go back to main menu!");
        var resp = ioService.awaitResponseString();
        if(resp.equalsIgnoreCase("exit")){
            sceneStackService.popScene();
            sceneStackService.pushScene(jumpToMainMenuScene);
        }else {
            playerCreationService.setPlayerName(resp);
            sceneStackService.popScene();
            sceneStackService.pushScene(worldGenScene);
        }
        return true;
    }
}
