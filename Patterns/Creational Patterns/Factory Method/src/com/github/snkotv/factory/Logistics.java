package com.github.snkotv.factory;

import com.github.snkotv.transport.Plane;
import com.github.snkotv.transport.Ship;
import com.github.snkotv.transport.Transport;
import com.github.snkotv.transport.Truck;

public class Logistics {

    private Transport transport = new Truck();

    public void getAddress() {
        System.out.println(transport);
    }

    public void deliverProduct(Configuration type) {
        transport = createTransport(type);
        transport.loadProduct();
        transport.deliver();
        getAddress();
    }

    private Transport createTransport(Configuration type) {
        switch (type)
        {
            case LAND:
                if (transport instanceof Truck) {
                    return transport;
                }
                return new Truck();
            case SEA:
                if (transport instanceof Ship) {
                    return transport;
                }
                return  new Ship();
            case AIR:
                if (transport instanceof Plane) {
                    return transport;
                }
                return new Plane();
            default:
                return new Plane();
        }
    }
}
