package com.github.snkotv.restaurant.recipes;

import com.github.snkotv.restaurant.dishes.Dish;

public abstract class Recipe {
    protected Dish dish;
    public void addBoneInBeefBroth() throws Exception {}
    public void addParsley() throws Exception {}
    public void addDill() throws Exception {}
    public void addCabbage() throws Exception {}
    public void addKvass() throws Exception {}
    public void addCarrot() throws Exception {}
    public void addBeetroot() throws Exception {}
    public void addOnion() throws Exception {}
    public void addMeat() throws Exception {}
    public void addSpices() throws Exception {}
    public void addTomato() throws Exception {}
    public void addCheese() throws Exception {}
    public void addOlive() throws Exception {}
    public void addCucumber() throws Exception {}
    public void addRice() throws Exception {}
    public abstract Dish serveDish();
}
