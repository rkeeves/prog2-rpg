package hu.unideb.inf.rpg.in;

import hu.unideb.inf.rpg.core.ecs.entity.Entity;
import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;
import hu.unideb.inf.rpg.core.ecs.event.EventManager;
import hu.unideb.inf.rpg.core.ecs.system.SubSystem;

import java.util.Scanner;

public class ConsoleInputSystem implements SubSystem {

    @Override
    public void onListenerSetup(EventManager events) {
        events.register(RequestInstantMessageInputEvent.class,this::onInstantUpdateTextInputEvent);
    }

    public boolean onInstantUpdateTextInputEvent(RequestInstantMessageInputEvent event, EventManager events, EntityManager entities) {
        String text = new Scanner(System.in).nextLine();;
        entities.forEach(entity -> this.update(entity,text));
        return  true;
    }

    private void update(Entity entity, String text){
        var consoleInputComponent = entity.getComponent(TextInputComponent.class);
        consoleInputComponent.setText(text);
    }
}
