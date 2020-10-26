package hu.unideb.inf;

import hu.unideb.inf.console.ConsoleInputAdapter;
import hu.unideb.inf.core.loop.BasicLooper;

public class GameStart {

    public static void main(String[] args) {
        new BasicLooper().loop(new RpgGame(new ConsoleInputAdapter()));
    }
}
