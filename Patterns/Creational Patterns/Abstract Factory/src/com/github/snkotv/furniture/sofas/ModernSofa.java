package com.github.snkotv.furniture.sofas;

public class ModernSofa implements Sofa {

    @Override
    public void sitOn()    {
        System.out.println("You're sitting on the Modern style sofa");
    }

    @Override
    public void lieOn() {
        System.out.println("You're lying on the Modern style sofa");
    }
}
