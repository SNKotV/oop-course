package com.github.snkotv.game.enemies;

public abstract class Enemy {
    protected String name;
    protected int hp;
    protected int attackPower;
    protected int level;

    public abstract Enemy clone();

    public void defend(int damage) {
        hp -= damage;
    }

    public int attack() {
        return attackPower;
    }

    public String getName() { return name; }

    public int getLevel() { return level; }

    public int getHP() {
        return hp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void upgrade() {
        level++;
        hp *= 2;
        attackPower *= 2;
    }

    @Override
    public String toString() {
        return "The " + name + " is " + level + " lvl. It has " + hp + " HP and " + attackPower + " attack power";
    }
}
