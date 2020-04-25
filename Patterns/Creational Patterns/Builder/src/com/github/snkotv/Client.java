package com.github.snkotv;

import com.github.snkotv.restaurant.dishes.Dish;
import com.github.snkotv.restaurant.recipes.BorschtRecipe;
import com.github.snkotv.restaurant.Cook;

import java.util.LinkedList;
import java.util.Random;

public class Client {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String makeChoise(LinkedList<String> menu) {
        System.out.println("\t-=Menu=-");
        for (String dish: menu) {
            System.out.println("\t|" + dish);
        }
        System.out.println();
        return menu.get(new Random().nextInt(menu.size()));
    }

    public void getOrderedDish(Dish dish) {
        dish.eat();
    }
}
