package com.github.snkotv.kitchen.cooker;

import com.github.snkotv.kitchen.Dish;

public class Cooker {
    private CookerImp cookerImp;
    private Dish dish;

    public void putDish(Dish dish) { this.dish = dish; }

    public void takeDish() { dish = null; }

    public void cook(int preferredTime) {
        if (preferredTime <= 10) {
            if (!(cookerImp instanceof MicrowaveOven)) {
                cookerImp = new MicrowaveOven();
            }
            cookerImp.cook(dish);
        } else if (preferredTime <= 20) {
            if (!(cookerImp instanceof Multicooker)) {
                cookerImp = new Multicooker();
            }
            cookerImp.cook(dish);
        } else {
            if (!(cookerImp instanceof Stove)) {
                cookerImp = new Stove();
            }
            cookerImp.cook(dish);
        }
    }

}
