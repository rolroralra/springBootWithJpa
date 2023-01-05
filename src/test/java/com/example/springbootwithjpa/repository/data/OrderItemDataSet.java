package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.OrderItem;

public class OrderItemDataSet {

    public static OrderItem testData() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(OrderDataSet.testData());
        orderItem.setItem(ItemDataSet.testData("Book"));

        return orderItem;
    }
}
