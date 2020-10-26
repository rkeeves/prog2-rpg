package hu.unideb.inf;

import hu.unideb.inf.core.loop.Loopable;
import hu.unideb.inf.event.GameEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RpgGame implements Loopable {

    final Iterable<GameEvent> eventProvider;

    int turnCount = 0;

    @Override
    public boolean shouldContinueRunning() {
        return turnCount < 3;
    }

    @Override
    public void update() {
        System.out.print("Enter something: ");
        var eventIterator = eventProvider.iterator();
        while (eventIterator.hasNext()) {
            System.out.println(eventIterator.next());
        }
        turnCount++;
    }

    @Override
    public void onLoopEnter() {
        System.out.println("onLoopEnter");
    }

    @Override
    public void onLoopExit() {
        System.out.println("onLoopExit");
    }
}
