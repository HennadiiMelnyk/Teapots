package com.test.melnyk.internetshop.service;

import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.Order;
import com.test.melnyk.internetshop.model.User;

import java.util.Map;

public interface OrderService {

    void insertNewOrder(Order order);

    Order getOrder(User user, Map<Item, Integer> cartItems);
}
