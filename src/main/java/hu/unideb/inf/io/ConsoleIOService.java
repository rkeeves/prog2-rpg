package hu.unideb.inf.io;

import java.util.Scanner;

public class ConsoleIOService implements IOService {

    @Override
    public String getPlayerName() {
        System.out.print("Please define your nick: ");
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public String awaitResponseString() {
        return new Scanner(System.in).nextLine();
    }
}
