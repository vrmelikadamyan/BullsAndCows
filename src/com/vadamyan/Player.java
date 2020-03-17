package com.vadamyan;

import java.util.Scanner;

public class Player {
    protected String number;
    protected int numberOfAttempts;

    Player() {
        number = new String();
        numberOfAttempts = 0;
    }

    public void tryToGuess() {
        Scanner in = new Scanner(System.in);
        number = in.nextLine();
    }
}
