package com.github.snkotv.furniture.coffee_tables;

public class DecoCoffeeTable implements CoffeeTable {

    @Override
    public void putOn(String thing) {
        System.out.println("You've put the " + thing + " on the Deco style coffee table");
    }
}
