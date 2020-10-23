package hu.unideb.inf.io;

public interface IOService {

    String getPlayerName();

    void displayMessage(String msg);

    String awaitResponseString();
}
