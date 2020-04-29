package com.github.snkotv.game.enemies;

public class Ogre extends Enemy {

    public Ogre(int hp, int attackPower) {
        this.name = "Ogre";
        this.hp = hp;
        this.attackPower = attackPower;
        this.level = 1;
    }

    private Ogre(Ogre ogre) {
        name = ogre.name;
        hp = ogre.hp;
        attackPower = ogre.attackPower;
        level = ogre.level;
    }

    @Override
    public Enemy clone() {
        return new Ogre(this);
    }
}
