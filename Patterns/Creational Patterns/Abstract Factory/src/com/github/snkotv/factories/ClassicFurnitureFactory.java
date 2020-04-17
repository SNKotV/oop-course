package com.github.snkotv.factories;

import com.github.snkotv.furniture.chairs.Chair;
import com.github.snkotv.furniture.chairs.ClassicChair;
import com.github.snkotv.furniture.coffee_tables.ClassicCoffeeTable;
import com.github.snkotv.furniture.coffee_tables.CoffeeTable;
import com.github.snkotv.furniture.sofas.ClassicSofa;
import com.github.snkotv.furniture.sofas.Sofa;

public class ClassicFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ClassicChair();
    }

    @Override
    public Sofa createSofa() {
        return new ClassicSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ClassicCoffeeTable();
    }
}
