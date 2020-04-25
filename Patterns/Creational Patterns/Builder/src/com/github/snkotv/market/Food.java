package com.github.snkotv.market;

public abstract class Food {
    protected String name;
    protected int nutritionalValue;

    @Override
    public String toString() {
        return name + " (" + nutritionalValue + " calories)";
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }
}
