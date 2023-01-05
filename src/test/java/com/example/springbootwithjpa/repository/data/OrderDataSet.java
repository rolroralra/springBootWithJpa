package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.Order;

public class OrderDataSet {

    public static Order testData() {
        Order order = new Order();
        order.setMember(MemberDataSet.testData("admin"));
        order.setDelivery(DeliveryDataSet.testData());

        return order;
    }
}
