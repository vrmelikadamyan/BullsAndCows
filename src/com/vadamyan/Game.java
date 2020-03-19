package com.vadamyan;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private int difficulty;
    private StringBuilder guessedNumber;
    private Player player;
    private int bulls;
    private int cows;

    public Game() {
        guessedNumber = new StringBuilder();
        player = new Player();
    }

    public void init() {
        System.out.print("Выберите сложность игры (от 3 до 5): ");
        difficulty = enterNumber();
        if (difficulty < 3 || difficulty > 5) {
            System.out.println("Вы ввели неверное значение. Игра завершена.");
        } else {
            makeANumber();
            start();
        }
    }

    public int enterNumber() {
        Scanner in = new Scanner(System.in);

        return in.nextInt();
    }

    public void start() {
        System.out.println(guessedNumber);
        String number;
        while (!player.isWin()) {
            do {
                System.out.print("Попробуйте угадать число: ");
                number = player.tryToGuess();
                if (!isNumberValid(number)) {
                    System.out.println("Вы ввели неверное значение! Попробуйте ещё раз.");
                }
            } while (!isNumberValid(number));

            if (number.equals("Сдаюсь") || number.equals("сдаюсь")) {
                finish();
                break;
            }

            player.inCreaseAttempts();
            checkNumber(number);
        }
    }

    public void makeANumber() {
        Random random = new Random();
        int[] number = new int[difficulty];
        int num;
        for (int i = 0; i < difficulty; ) {
            num = random.nextInt(10);
            boolean b = true;
            for (int j = 0; j < i; ++j) {
                if (number[j] == num) {
                    b = false;
                    break;
                }
            }
            if (b) {
                number[i] = num;
                guessedNumber.append(number[i]);
                ++i;
            }
        }
    }

    public void checkNumber(String number) {
        for (int i = 0; i < guessedNumber.length(); ++i) {
            for (int j = 0; j < number.length(); ++j) {
                if (number.charAt(j) == guessedNumber.charAt(i)) {
                    if (i == j) {
                        ++bulls;
                    } else {
                        ++cows;
                    }
                }
            }
        }

        if (bulls == difficulty) {
            player.setWin(true);
            finish();
        } else {
            printAnswer();
        }
    }

    public void printAnswer() {
        System.out.println("Быки: " + bulls + "\nКоровы: " + cows);
        bulls = 0;
        cows = 0;
    }

    public boolean isNumberValid(String number) {
        boolean isValid = true;
        if (number.length() == difficulty) {
            for (int i = 0; i < number.length(); ++i) {
                for (int j = 0; j < i; ++j) {
                    if (number.charAt(i) == number.charAt(j)) {
                        isValid = false;
                        break;
                    }
                }
            }
        } else {
            return (number.equals("Сдаюсь") || number.equals("сдаюсь"));
        }

        return isValid;
    }

    public void finish() {
        if (player.isWin()) {
            System.out.println("Победа!");
        } else {
            System.out.println("Поражение!");
        }

        System.out.println("Загаданное число: " + guessedNumber);
        player.showStat();
    }
}
