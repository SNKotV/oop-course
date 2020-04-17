package com.github.snkotv.transport;

public class Truck implements Transport {


    @Override
    public void loadProduct() {
        System.out.println("Product is loaded on the truck");
    }

    @Override
    public void deliver() {
        System.out.println("Delivered by truck");
    }
}
