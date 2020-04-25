package com.github.snkotv.restaurant.recipes;

import com.github.snkotv.market.Seller;
import com.github.snkotv.restaurant.dishes.Borscht;
import com.github.snkotv.restaurant.dishes.Dish;

public class BorschtRecipe extends Recipe {

    public BorschtRecipe() {
        dish = new Borscht();
    }

    @Override
    public void addBoneInBeefBroth() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Bone-in beef broth"));
    }

    @Override
    public void addParsley() throws Exception{
        dish.addIngredient(Seller.callSeller().makeOrder("Parsley"));
    }

    @Override
    public void addDill()throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Dill"));
    }

    @Override
    public void addCabbage() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Cabbage"));
    }


    @Override
    public void addKvass() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Kvass"));
    }

    @Override
    public void addCarrot() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Carrot"));
    }

    @Override
    public void addBeetroot() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Beetroot"));
    }

    @Override
    public void addOnion() throws Exception {
        dish.addIngredient(Seller.callSeller().makeOrder("Onion"));
    }

    public Dish serveDish() {
        Dish cookedDish = dish;
        dish = new Borscht();
        return cookedDish;
    }
}
