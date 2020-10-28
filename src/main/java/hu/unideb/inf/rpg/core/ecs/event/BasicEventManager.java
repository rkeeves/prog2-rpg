package hu.unideb.inf.rpg.core.ecs.event;

import hu.unideb.inf.rpg.core.ecs.entity.EntityManager;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class BasicEventManager implements EventManager{

    @Setter
    private EntityManager entityManager;

    private final Map<Class<?>, List<EventListener<?>>> consumers = new HashMap<>();

    @Override
    public <E> void register(Class<E> eventClass, EventListener<E> consumer) {
        getListenersFor(eventClass).add(consumer);
    }

    @Override
    public <E> void fire(E event) {
        getListenersFor(event.getClass())
                .stream()
                .allMatch(listener -> ((EventListener<E>)listener).accept(event, this, entityManager));
    }

    private List<EventListener<?>> getListenersFor(Class<?> eventCls){
        return consumers
                .computeIfAbsent(eventCls,e->new LinkedList<>());
    }
}
