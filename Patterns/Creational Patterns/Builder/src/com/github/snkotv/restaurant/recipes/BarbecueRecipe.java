package com.github.snkotv.restaurant.recipes;

import com.github.snkotv.market.Seller;
import com.github.snkotv.restaurant.dishes.Barbecue;
import com.github.snkotv.restaurant.dishes.Dish;

public class BarbecueRecipe extends Recipe {

    public BarbecueRecipe() {
        dish = new Barbecue();
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
    public Dish serveDish() {
        Dish cookedDish = dish;
        dish = new Barbecue();
        return cookedDish;
    }
}
