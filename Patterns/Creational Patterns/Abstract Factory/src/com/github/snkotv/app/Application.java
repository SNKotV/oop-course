package com.github.snkotv.app;

import com.github.snkotv.factories.FurnitureFactory;
import com.github.snkotv.furniture.chairs.Chair;
import com.github.snkotv.furniture.coffee_tables.CoffeeTable;
import com.github.snkotv.furniture.sofas.Sofa;

public class Application {
    private Chair chair;
    private CoffeeTable coffeeTable;
    private Sofa sofa;

    public Application(FurnitureFactory factory) {
        chair = factory.createChair();
        coffeeTable = factory.createCoffeeTable();
        sofa = factory.createSofa();
    }

    public void useFurniture() {
        chair.sitOn();
        coffeeTable.putOn("Cup of coffee");
        sofa.lieOn();
    }

}
