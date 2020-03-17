package com.vadamyan;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private int difficulty;
    private String guessedNumber;
    private Player player;
    private boolean isWin;
    private int bulls;
    private int cows;

    public Game() {
        difficulty = 0;
        guessedNumber = new String();
        player = new Player();
        isWin = false;
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
        while (!isWin) {
            do {
                System.out.print("Попробуйте угадать число: ");
                player.tryToGuess();
                if (!isNumberValid()) {
                    System.out.println("Вы ввели неверное значение! Попробуйте ещё раз.");
                }
            } while (!isNumberValid());

            if (player.number.equals("Сдаюсь") || player.number.equals("сдаюсь")) {
                isWin = false;
                finish();
            }

            player.numberOfAttempts++;
            checkNumber();
        }

    }

    public void checkNumber() {
        for (int i = 0; i < guessedNumber.length(); ++i) {
            for (int j = 0; j < player.number.length(); ++j) {
                if (player.number.charAt(j) == guessedNumber.charAt(i)) {
                    if (i == j) {
                        ++bulls;
                    } else {
                        ++cows;
                    }
                }
            }
        }

        if (bulls == difficulty) {
            isWin = true;
            finish();
        } else {
            System.out.println("Быки: " + bulls + "\nКоровы: " + cows);
            bulls = 0;
            cows= 0;
        }
    }

    public boolean isNumberValid() {
        boolean isValid = true;
            if (player.number.length() == difficulty) {
                for (int i = 0; i < player.number.length(); ++i) {
                    for (int j = 0; j < i; ++j) {
                        if (player.number.charAt(i) == player.number.charAt(j)) {
                            isValid = false;
                            break;
                        }
                    }
                }
            } else {
                return (player.number.equals("Сдаюсь") || player.number.equals("сдаюсь"));
            }

            return isValid;
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
                guessedNumber += number[i];
                ++i;
            }
        }
    }

    public void finish() {
        if (isWin) {
            System.out.println("Победа!");
        } else {
            System.out.println("Поражение!");
        }

        System.out.println("Загаданное число: " + guessedNumber + "\nПопыток: " + player.numberOfAttempts);
    }
}
