package com.github.snkotv;

import com.github.snkotv.factory.Logistics;
import com.github.snkotv.factory.Configuration;

public class Client {
    private static Logistics logistics = new Logistics();

    public static void main(String[] args) {

        runTransportation(Configuration.LAND);
        System.out.println();
        runTransportation(Configuration.LAND);
        System.out.println();
        runTransportation(Configuration.SEA);

    }



    private static void runTransportation(Configuration type) {
        logistics.deliverProduct(type);
    }

}
