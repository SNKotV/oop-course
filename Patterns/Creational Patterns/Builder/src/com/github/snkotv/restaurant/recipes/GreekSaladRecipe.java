package com.github.snkotv.restaurant.recipes;

import com.github.snkotv.market.Seller;
import com.github.snkotv.restaurant.dishes.Dish;
import com.github.snkotv.restaurant.dishes.GreekSalad;

public class GreekSaladRecipe extends Recipe {

    public GreekSaladRecipe() {
        dish = new GreekSalad();
    }

    @Override
    public void addTomato() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Tomato"));
    }

    @Override
    public void addCheese() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Cheese"));
    }

    @Override
    public void addCabbage() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Cabbage"));
    }

    @Override
    public void addOlive() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Olive"));
    }

    @Override
    public void addSpices() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Spices"));
    }

    @Override
    public void addCucumber() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Cucumber"));
    }

    @Override
    public Dish serveDish() {
        Dish cookedDish = dish;
        dish = new GreekSalad();
        return cookedDish;
    }
}
