package com.github.snkotv.kitchen.cooker;

import com.github.snkotv.kitchen.Dish;
import com.github.snkotv.kitchen.DishQuality;

public class Stove implements CookerImp {

    @Override
    public void cook(Dish dish) {
        dish.cook(DishQuality.HIGH, 25);
    }
}
