package com.github.snkotv.furniture.sofas;

public class VictorianSofa implements Sofa {

    @Override
    public void sitOn() {
        System.out.println("You're sitting on the Victorian style sofa");
    }

    @Override
    public void lieOn() {
        System.out.println("You're lying on the Victorian style sofa");
    }
}
