package com.github.snkotv.game;

import com.github.snkotv.game.enemies.Enemy;
import com.github.snkotv.game.player.Player;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Game instance = null;

    private Game() {
        init();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    private Player player;
    private boolean isBlocked;
    private Difficulty difficulty;
    private EnemyCamp enemyCamp;
    private Scanner stdInput;

    private void init() {
        stdInput = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = stdInput.nextLine();

        player = new Player(name, 30, 5);

        difficulty = selectDifficulty();

        enemyCamp = new EnemyCamp(difficulty);
        stdInput.nextLine();
        System.out.println("\nTo win you need to defeat:");
        enemyCamp.show();
        System.out.println();
    }

    private Difficulty selectDifficulty() {
        try {
            System.out.println("Enter 1,2 or 3 to select one of the difficulties: ");
            System.out.print("1. Easy\t2. Normal\t3. Hard\n>:");
            int index = stdInput.nextInt();

            if (index < 0 || index > 3) {
                System.out.println("You've inputted invalid index!");
                return selectDifficulty();
            }

            if (index == 1) {
                return Difficulty.EASY;
            } else if (index == 2) {
                return Difficulty.NORMAL;
            } else {
                return Difficulty.HARD;
            }

        } catch (InputMismatchException e) {
            System.out.println("You've inputted invalid index!");
            stdInput.nextLine();
            return selectDifficulty();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

    private void run() {
        Random rand = new Random();
        while (player.isAlive()) {
            int availableActions = 1 + rand.nextInt(4);
            isBlocked = false;
            for (int i = 0; i < availableActions; i++) {
                if (enemyCamp.isDefeated()) {
                    System.out.println("Congratulations!!! You're WINNER !!!");
                    return;
                }
                if (!isBlocked) {
                    makeAction();
                }
            }
            if (enemyCamp.isDefeated()) {
                System.out.println("\nCongratulations!!! You're WINNER !!!");
                return;
            }
            if (isBlocked) {
                System.out.println("\nYou've blocked all damage");
            } else {
                int damage = enemyCamp.peekUnit().attack();
                player.defend(damage);
                System.out.println("\nYou've been taken " + damage + " damage " + player.getHP() + " HP left");
            }
        }
        System.out.println("\nYou've been defeated!");
        System.out.println("Enemies remaining:");
        enemyCamp.show();
    }

    private void makeAction() {
        System.out.print("\nEnter \'a\' for \"attack\" or \'b\' for \"block\": ");
        String action = stdInput.nextLine();
        if (action.equals("a")) {
            int damage = player.attack();
            Enemy unit = enemyCamp.peekUnit();
            System.out.println("You're dealing " + damage + " damage to " + unit.getName() + " " + unit.getLevel() + " lvl");
            unit.defend(damage);
            if (!unit.isAlive()) {
                System.out.println("You've killed the " + unit.getName() + " " + unit.getLevel() + " lvl");
                enemyCamp.buryUnit();
            } else {
                System.out.println("It has " + unit.getHP() + " HP now");
            }
        } else if (action.equals("b")) {
            isBlocked = true;
        } else {
            System.out.println("Invalid command!");
            makeAction();
        }
    }

    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.run();
    }
}
