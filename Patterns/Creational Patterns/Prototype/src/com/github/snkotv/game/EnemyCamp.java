package com.github.snkotv.game;

import com.github.snkotv.game.enemies.Cyclops;
import com.github.snkotv.game.enemies.Enemy;
import com.github.snkotv.game.enemies.Goblin;
import com.github.snkotv.game.enemies.Ogre;

import java.util.LinkedList;

public class EnemyCamp {
    private LinkedList<Enemy> army;

    public EnemyCamp(Difficulty difficulty) {
         army = new LinkedList<>();
         createCamp(difficulty);
    }

    public void createCamp(Difficulty difficulty) {
        int amount = 3;

        Goblin goblin = new Goblin(5, 5);
        addArmy(goblin, amount);

        Ogre ogre = new Ogre(10, 10);
        addArmy(ogre, amount);

        Cyclops cyclops = new Cyclops(20, 20);
        addArmy(cyclops, amount);

        if (difficulty == Difficulty.EASY) {
            return;
        }

        goblin.upgrade();
        addArmy(goblin, amount);
        ogre.upgrade();
        addArmy(ogre, amount);
        cyclops.upgrade();
        addArmy(cyclops, amount);

        if (difficulty == Difficulty.NORMAL) {
            return;
        }

        goblin.upgrade();
        addArmy(goblin, amount);
        ogre.upgrade();
        addArmy(ogre, amount);
        cyclops.upgrade();
        addArmy(cyclops, amount);
    }

    private void addArmy(Enemy unit, int amount) {
        for (int i = 0; i < amount; i++) {
            Enemy newUnit = unit.clone();
            army.add(newUnit);
        }
    }

    public Enemy peekUnit() {
        return army.getFirst();
    }

    public void buryUnit() {
        army.removeFirst();
    }

    public void show() {
        for (Enemy e: army) {
            System.out.println(e);
        }
    }

    public boolean isDefeated() {
        return army.isEmpty();
    }

}
