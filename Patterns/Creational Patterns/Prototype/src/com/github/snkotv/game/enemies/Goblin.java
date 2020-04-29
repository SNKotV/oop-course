package com.github.snkotv.game.enemies;

public class Goblin extends Enemy {
    public Goblin(int hp, int attackPower) {
        this.name = "Goblin";
        this.hp = hp;
        this.attackPower = attackPower;
        level = 1;
    }

    private Goblin(Goblin goblin) {
        name = goblin.name;
        hp = goblin.hp;
        attackPower = goblin.attackPower;
        level = goblin.level;
    }

    @Override
    public Enemy clone() {
        return new Goblin(this);
    }
}
