package com.github.snkotv.transport;

public class Ship implements Transport {

    @Override
    public void loadProduct() {
        System.out.println("Product is loaded on the ship");
    }
    @Override
    public void deliver()    {
        System.out.println("Delivered by ship");
    }
}
