package com.github.snkotv.restaurant.recipes;

import com.github.snkotv.market.Seller;
import com.github.snkotv.restaurant.dishes.Dish;
import com.github.snkotv.restaurant.dishes.Plov;

public class PlovRecipe extends Recipe {

    public PlovRecipe() {
        dish = new Plov();
    }

    @Override
    public void addRice() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Rice"));
    }

    @Override
    public void addMeat() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Meat"));
    }

    @Override
    public void addSpices() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Spices"));
    }

    @Override
    public void addCarrot() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Carrot"));
    }

    @Override
    public void addDill() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Dill"));
    }

    @Override
    public Dish serveDish() {
        Dish cookedDish = dish;
        dish = new Plov();
        return cookedDish;
    }
}
