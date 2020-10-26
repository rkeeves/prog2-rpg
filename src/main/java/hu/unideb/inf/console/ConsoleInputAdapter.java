package hu.unideb.inf.console;

import hu.unideb.inf.event.GameEvent;

import java.util.Iterator;

public class ConsoleInputAdapter implements Iterable<GameEvent> {

    private final ConsoleCharacterIterator consoleTextEventPoller = new ConsoleCharacterIterator();

    @Override
    public Iterator<GameEvent> iterator() {
        return new KeyPressedEventIteratorConsoleAdapter(consoleTextEventPoller.iterator());
    }
}
