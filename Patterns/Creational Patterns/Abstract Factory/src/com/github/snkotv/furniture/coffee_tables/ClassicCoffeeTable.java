package com.github.snkotv.furniture.coffee_tables;

public class ClassicCoffeeTable implements CoffeeTable {


    @Override
    public void putOn(String thing) {
        System.out.println("Classic coffee table");
    }
}
