package com.github.snkotv.game.player;

public class Player {
    private String name;
    private int hp;
    private int attackPower;

    public Player(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public int getHP() { return hp; }
    public void defend(int damage) {
        hp -= damage;
    }

    public int attack() {
        return attackPower;
    }

    public boolean isAlive() {
        return hp > 0;
    }
}
