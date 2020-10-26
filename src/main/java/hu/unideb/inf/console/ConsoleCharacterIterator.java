package hu.unideb.inf.console;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleCharacterIterator implements Iterable<Character> {

    @Override
    public Iterator<Character> iterator() {
        return stringIterator(new Scanner(System.in).nextLine());
    }

    private static Iterator<Character> stringIterator(final String string) {
        if (string == null)
            throw new NullPointerException();

        return new Iterator<>() {
            private int index = 0;

            public boolean hasNext() {
                return index < string.length();
            }

            public Character next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return string.charAt(index++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
