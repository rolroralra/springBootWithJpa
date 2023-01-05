package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.Delivery;

public class DeliveryDataSet {
    public static Delivery testData() {
        Delivery delivery = new Delivery();
        delivery.setAddress(AddressDataSet.testData());

        return delivery;
    }
}
