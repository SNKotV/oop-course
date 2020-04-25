package com.github.snkotv.market;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public class Seller {
    private static Seller instance = null;
    private static final Map<String, Callable<Food>> foodList = Collections.unmodifiableMap(new TreeMap<>() {
        {
            put("Bone-in beef broth", () -> new BoneInBeffBroth());
            put("Parsley", () -> new Parsley());
            put("Dill", () -> new Dill());
            put("Cabbage", () -> new Cabbage());
            put("Kvass", () -> new Kvass());
            put("Carrot", () -> new Carrot());
            put("Beetroot", () -> new Beetroot());
            put("Onion", () -> new Onion());
            put("Meat", () -> new Meat());
            put("Spices", () -> new Spices());
            put("Tomato", () -> new Tomato());
            put("Cheese", () -> new Cheese());
            put("Olive", () -> new Olive());
            put("Cucumber", () -> new Cucumber());
            put("Rice", () -> new Rice());
        }
    });

    private Seller() {}

    public static Seller callSeller() {
        if (instance == null) {
            instance = new Seller();
        }
        return instance;
    }

    public Food makeOrder(String foodName) throws Exception {
        return foodList.get(foodName).call();
    }
}
