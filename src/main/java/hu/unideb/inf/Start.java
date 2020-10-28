package hu.unideb.inf;

import hu.unideb.inf.rpg.core.ecs.ECS;
import hu.unideb.inf.rpg.core.ecs.system.SubSystem;
import hu.unideb.inf.rpg.out.ConsoleOutputSystem;
import hu.unideb.inf.rpg.player.PlayerSubsystem;
import hu.unideb.inf.rpg.spawn.SpawnSystem;
import hu.unideb.inf.rpg.in.ConsoleInputSystem;

import java.util.Arrays;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        List<SubSystem> subSystems = Arrays.asList(new SpawnSystem(),new PlayerSubsystem(),new ConsoleInputSystem(), new ConsoleOutputSystem());
        var ecs = new ECS(subSystems);
        ecs.configure();
        ecs.beforeFirstUpdate();
        // some kind of game loop
        // this is just a placeholder
        for (int i = 0; i < 3; i++) {
            ecs.update();
        }

    }

}
