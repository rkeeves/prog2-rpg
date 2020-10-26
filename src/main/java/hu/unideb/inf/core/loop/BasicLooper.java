package hu.unideb.inf.core.loop;

public class BasicLooper<EW,EG>  implements Looper {

    @Override
    public void loop(Loopable loopable) {
        loopable.onLoopEnter();
        while(loopable.shouldContinueRunning()) {
            loopable.update();
        }
        loopable.onLoopExit();
    }
}
