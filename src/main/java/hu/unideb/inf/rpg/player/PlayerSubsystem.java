package hu.unideb.inf.rpg.player;

import hu.unideb.inf.rpg.out.RequestInstantMessageOutputEvent;
import hu.unideb.inf.rpg.in.RequestInstantMessageInputEvent;
import hu.unideb.inf.rpg.in.TextInputComponent;
import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import hu.unideb.inf.rpg.core.ecs.system.SubSystem;

public class PlayerSubsystem implements SubSystem {

    @Override
    public void onListenerSetup(EventManager events) {
        events.register(PlayerSpawnedEvent.class,this::onPlayerSpawnedEvent);
    }

    public boolean onPlayerSpawnedEvent(PlayerSpawnedEvent event, EventManager eventManager, EntityManager entityManager){
        var playerEntity = event.getPlayerEntity();
        var playerComponent = playerEntity.getComponent(PlayerComponent.class);
        entityManager.attach(playerEntity, new TextInputComponent());
        eventManager.fire(new RequestInstantMessageOutputEvent("Please enter your name: "));
        eventManager.fire(new RequestInstantMessageInputEvent());
        var consoleInputComponent = playerEntity.getComponent(TextInputComponent.class);
        playerComponent.setName(consoleInputComponent.getText());
        return true;
    }
}
