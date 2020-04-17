package com.github.snkotv.furniture.sofas;

public class ClassicSofa implements Sofa {
    @Override
    public void sitOn() {
        System.out.println("Sitting on the Classic sofa");
    }

    @Override
    public void lieOn() {
        System.out.println("Lying on the Classic sofa");
    }
}
