package com.github.snkotv.kitchen;

import com.github.snkotv.kitchen.cooker.Cooker;

public class Man {
    public static void main(String[] args) {
        Dish dish1 = new Dish();
        Dish dish2 = new Dish();
        Dish dish3 = new Dish();

        Cooker cooker = new Cooker();

        dish1.eat();
        System.out.println();
        cooker.putDish(dish1);
        cooker.cook(7);
        cooker.takeDish();
        dish1.eat();
        System.out.println();

        System.out.println();
        cooker.putDish(dish2);
        cooker.cook(17);
        cooker.takeDish();
        dish2.eat();
        System.out.println();

        System.out.println();
        cooker.putDish(dish3);
        cooker.cook(37);
        cooker.takeDish();
        dish3.eat();
        System.out.println();

    }
}
