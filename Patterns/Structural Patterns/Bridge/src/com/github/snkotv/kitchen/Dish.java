package com.github.snkotv.kitchen;

public class Dish {
    private boolean isCooked = false;
    private DishQuality quality;
    private int cookingTime;

    public void cook(DishQuality quality, int cookingTime) {
        isCooked = true;
        this.quality = quality;
        this.cookingTime = cookingTime;
    }

    public boolean isCooked() { return isCooked;  }

    public void eat() {
        if (!isCooked()) {
            System.out.println("This dish isn't cooked yet");
        } else {
            System.out.println("You,ve eaten the dish with " + quality + " quality. It has been cooked for a " + cookingTime + " minutes");
        }
    }
}
