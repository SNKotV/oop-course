package com.github.snkotv.restaurant;

import com.github.snkotv.restaurant.dishes.Dish;
import com.github.snkotv.restaurant.recipes.Recipe;

public class Cook {
    private Recipe recipe;

    public Cook(Recipe recipe) {
        this.recipe = recipe;
    }

    public void changeRecipe(Recipe newRecipe) {
        recipe = newRecipe;
    }

    public Dish cookDish() throws Exception {
        recipe.addBoneInBeefBroth();
        recipe.addParsley();
        recipe.addDill();
        recipe.addCabbage();
        recipe.addKvass();
        recipe.addCarrot();
        recipe.addBeetroot();
        recipe.addOnion();
        recipe.addMeat();
        recipe.addSpices();
        recipe.addTomato();
        recipe.addCheese();
        recipe.addOlive();
        recipe.addCucumber();
        recipe.addRice();
        return recipe.serveDish();
    }
}
