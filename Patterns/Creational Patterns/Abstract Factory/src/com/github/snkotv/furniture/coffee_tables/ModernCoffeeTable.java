package com.github.snkotv.furniture.coffee_tables;

public class ModernCoffeeTable implements CoffeeTable {

    @Override
    public void putOn(String thing) {
        System.out.println("You've put the " + thing + " on the Modern style coffee table");
    }
}
