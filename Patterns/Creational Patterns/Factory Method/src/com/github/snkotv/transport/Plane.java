package com.github.snkotv.transport;

public class Plane implements Transport {

    @Override
    public void loadProduct() {
        System.out.println("Product is loaded on the plane");
    }

    @Override
    public void deliver() {
        System.out.println("Delivered by plane");
    }
}
