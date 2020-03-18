package com.vadamyan;

import java.util.Scanner;

public class Player {
    private int attempts;

    Player() {
    }

    public String tryToGuess() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void inCreaseAttempts() {
        ++attempts;
    }

    public void showStat() {
        System.out.println("Попыток: " + attempts);
    }
}
