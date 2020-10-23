package hu.unideb.inf;

import hu.unideb.inf.factory.EnemyCreationService;
import hu.unideb.inf.factory.PlayerCreationService;
import hu.unideb.inf.io.ConsoleIOService;
import hu.unideb.inf.io.IOService;
import hu.unideb.inf.scene.*;

import java.util.Optional;

public class Application {

    private final SceneStackService sceneStackService = new BasicSceneStackService();

    private final PlayerCreationService playerCreationService = new PlayerCreationService();

    private final EnemyCreationService enemyCreationService = new EnemyCreationService();

    void run(String[] args){
        IOService ioService = new ConsoleIOService();
        JumpToMainMenuScene jumpToMainMenuScene = new JumpToMainMenuScene(ioService);
        HighScoreScene highScoreScene = new HighScoreScene(ioService,jumpToMainMenuScene);
        PlayScene playScene = new PlayScene(ioService,highScoreScene,jumpToMainMenuScene,playerCreationService,enemyCreationService);
        WorldGenScene worldGenScene = new WorldGenScene(ioService,playScene,jumpToMainMenuScene);
        CharGenScene charGenScene = new CharGenScene(ioService,worldGenScene,jumpToMainMenuScene,playerCreationService);
        MainMenuScene mainMenuScene = new MainMenuScene(ioService,charGenScene);
        jumpToMainMenuScene.setMainMenuScene(mainMenuScene);
        sceneStackService.pushScene(mainMenuScene);
        boolean running = true;
        Optional<Scene> optActiveScene;
        while(running){
            optActiveScene = sceneStackService.getActiveScene();
            running = optActiveScene.map(scene -> scene.onTurn(sceneStackService)).orElse(false);
        }
        while(sceneStackService.hasActiveScene()){
            sceneStackService.popScene();
        }
    }
}
