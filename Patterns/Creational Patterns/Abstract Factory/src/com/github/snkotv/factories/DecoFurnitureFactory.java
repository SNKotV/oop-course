package com.github.snkotv.factories;

import com.github.snkotv.furniture.chairs.Chair;
import com.github.snkotv.furniture.chairs.DecoChair;
import com.github.snkotv.furniture.coffee_tables.CoffeeTable;
import com.github.snkotv.furniture.coffee_tables.DecoCoffeeTable;
import com.github.snkotv.furniture.sofas.DecoSofa;
import com.github.snkotv.furniture.sofas.Sofa;

public class DecoFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new DecoChair();
    }

    @Override
    public Sofa createSofa() {
        return new DecoSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new DecoCoffeeTable();
    }
}
