package com.github.snkotv.factories;

import com.github.snkotv.furniture.chairs.Chair;
import com.github.snkotv.furniture.chairs.VictorianChair;
import com.github.snkotv.furniture.coffee_tables.CoffeeTable;
import com.github.snkotv.furniture.coffee_tables.VictorianCoffeeTable;
import com.github.snkotv.furniture.sofas.Sofa;
import com.github.snkotv.furniture.sofas.VictorianSofa;

public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
}
