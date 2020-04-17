package com.github.snkotv.factories;

import com.github.snkotv.furniture.chairs.Chair;
import com.github.snkotv.furniture.chairs.ModernChair;
import com.github.snkotv.furniture.coffee_tables.CoffeeTable;
import com.github.snkotv.furniture.coffee_tables.ModernCoffeeTable;
import com.github.snkotv.furniture.sofas.ModernSofa;
import com.github.snkotv.furniture.sofas.Sofa;

public class ModernFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}
