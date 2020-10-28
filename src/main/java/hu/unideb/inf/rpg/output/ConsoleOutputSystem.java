package hu.unideb.inf.rpg.output;

import hu.unideb.inf.rpg.core.ecs.entity.Entity;
import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import hu.unideb.inf.rpg.core.ecs.system.SubSystem;
import hu.unideb.inf.rpg.player.PlayerComponent;

public class ConsoleOutputSystem implements SubSystem {

    @Override
    public void onListenerSetup(EventManager events) {
        events.register(RequestInstantMessageOutputEvent.class,this::onInstantMessageEvent);
    }

    public boolean onInstantMessageEvent(RequestInstantMessageOutputEvent event, EventManager events, EntityManager entities) {
        System.out.print(event.getMessage());
        return true;
    }

    @Override
    public void onUpdate(EventManager events, EntityManager entities) {
        System.out.println("========================================");
        System.out.println("This is a recurring display event, like rendering in each frame");
        entities.forEach(this::displayPlayerEntity,PlayerComponent.class);
    }

    private void displayPlayerEntity(Entity entity){
        var playerComponent = entity.getComponent(PlayerComponent.class);
        System.out.println("Player "+playerComponent.getName());
    }
}
