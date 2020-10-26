package hu.unideb.inf.core.loop;

public interface Loopable {

    boolean shouldContinueRunning();

    void update();

    void onLoopEnter();

    void onLoopExit();
}
