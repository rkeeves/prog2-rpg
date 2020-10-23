package hu.unideb.inf.scene;

import hu.unideb.inf.factory.EnemyCreationService;
import hu.unideb.inf.factory.PlayerCreationService;
import hu.unideb.inf.io.IOService;
import hu.unideb.inf.room.FirstRoom;
import lombok.Data;

@Data
public class PlayScene implements Scene{

    private final IOService ioService;

    private final HighScoreScene highScoreScene;

    private final JumpToMainMenuScene jumpToMainMenuScene;

    private final PlayerCreationService playerCreationService;

    private final EnemyCreationService enemyCreationService;

    @Override
    public boolean onTurn(SceneStackService sceneStackService) {
        FirstRoom firstRoom = new FirstRoom(playerCreationService,enemyCreationService);
        firstRoom.enter();
        sceneStackService.popScene();
        sceneStackService.pushScene(highScoreScene);
        return true;
    }
}
