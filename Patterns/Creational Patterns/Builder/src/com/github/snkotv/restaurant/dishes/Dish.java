package com.github.snkotv.restaurant.dishes;

import com.github.snkotv.market.*;
import java.util.LinkedList;

public abstract class Dish {
    protected String name;
    protected LinkedList<Food> ingredients;
    protected int nutritionalValue;

    public Dish() {
        ingredients = new LinkedList<>();
    }

    public void addIngredient(Food ingredient) {
        nutritionalValue += ingredient.getNutritionalValue();
        ingredients.add(ingredient);
    }

    public void eat() {
        if (ingredients == null) {
            System.out.println("This \"" + name + "\" has been cooked wrong! It's dangerous to eat it.");
            System.out.println("You should use the recipe for future cooking!");
            return;
        }

        System.out.println("You're have eaten the \"" + name + "\"");
        System.out.println("It's consisted of:");
        for (Food ingredient: ingredients) {
            System.out.println("\t- " + ingredient);
        }
        System.out.println("Nutritional value: " + nutritionalValue + " calories\n");
    }
}
