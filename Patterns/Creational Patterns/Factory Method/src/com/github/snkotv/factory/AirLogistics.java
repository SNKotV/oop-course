package com.github.snkotv.factory;

import com.github.snkotv.transport.Plane;
import com.github.snkotv.transport.Transport;

public class AirLogistics extends  Logistics {

    public Transport createTransport() {
        return new Plane();
    }
}
