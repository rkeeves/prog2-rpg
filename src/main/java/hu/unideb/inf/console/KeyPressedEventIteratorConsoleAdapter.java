package hu.unideb.inf.console;

import hu.unideb.inf.event.GameEvent;
import hu.unideb.inf.event.KeyPressedEvent;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;

@RequiredArgsConstructor
public class KeyPressedEventIteratorConsoleAdapter implements Iterator<GameEvent> {

    private final Iterator<Character> consoleTextEventIterator;

    @Override
    public boolean hasNext() {
        return consoleTextEventIterator.hasNext();
    }

    @Override
    public KeyPressedEvent next() {
        return new KeyPressedEvent(consoleTextEventIterator.next());
    }
}
