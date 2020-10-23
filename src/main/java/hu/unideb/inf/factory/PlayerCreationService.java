package hu.unideb.inf.factory;

import hu.unideb.inf.io.ConsoleIOService;
import hu.unideb.inf.io.IOService;
import hu.unideb.inf.model.Player;

public class PlayerCreationService implements EntityFactory{

    private String playerName = "";

    @Override
    public Player create() {
        IOService ioService = new ConsoleIOService();

        return Player.builder()
                .name(playerName)
                .gold(10)
                .damage(5)
                .health(100)
                .experiencePoints(0)
                .level(1)
                .build();
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }
}
