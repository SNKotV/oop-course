package com.github.snkotv.furniture.sofas;

public class DecoSofa implements Sofa {

    @Override
    public void sitOn() {
        System.out.println("You're sitting on the Deco style sofa");
    }

    @Override
    public void lieOn() {
        System.out.println("You're lying on the Deco style sofa");
    }
}
