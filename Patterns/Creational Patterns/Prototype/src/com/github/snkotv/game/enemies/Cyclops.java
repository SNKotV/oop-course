package com.github.snkotv.game.enemies;

public class Cyclops extends Enemy{
    public Cyclops(int hp, int attackPower) {
        this.name = "Cyclops";
        this.hp = hp;
        this.attackPower = attackPower;
        this.level = 1;
    }

    private Cyclops(Cyclops cyclops) {
        name = cyclops.name;
        hp = cyclops.hp;
        attackPower = cyclops.attackPower;
        level = cyclops.level;
    }

    @Override
    public Enemy clone() {
        return new Cyclops(this);
    }
}
