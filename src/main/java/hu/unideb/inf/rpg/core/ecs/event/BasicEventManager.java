package hu.unideb.inf.rpg.core.ecs.event;

import hu.unideb.inf.rpg.core.ecs.component.EntityManager;
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
        fetchList(eventClass).add(consumer);
    }

    @Override
    public <E> void fire(E event) {
        fetchList(event.getClass())
                .stream()
                .anyMatch(cons->((EventListener<E>)cons).accept(event,this,entityManager));
    }

    private List<EventListener<?>> fetchList(Class<?> eventCls){
        return consumers
                .computeIfAbsent(eventCls,e->new LinkedList<>());
    }
}
