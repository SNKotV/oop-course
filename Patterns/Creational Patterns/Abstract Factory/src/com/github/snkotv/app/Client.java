package com.github.snkotv.app;

import com.github.snkotv.factories.*;

enum Configuration {DECO, MODERN, VICTORIAN};

public class Client {
    private static FurnitureFactory factory = new DecoFurnitureFactory();

    public static void main(String[] args) {
        Application app = new Application(factory);
        app.useFurniture();
        System.out.println();
        app = new Application(new ModernFurnitureFactory());
        app.useFurniture();
        System.out.println();
        app = new Application(new ClassicFurnitureFactory());
        app.useFurniture();
    }
}
