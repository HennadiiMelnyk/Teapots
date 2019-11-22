package com.test.melnyk.internetshop.service.impl;

import com.test.melnyk.internetshop.dao.entitydao.OrderDao;
import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.Order;
import com.test.melnyk.internetshop.model.OrderStatus;
import com.test.melnyk.internetshop.model.OrderedItem;
import com.test.melnyk.internetshop.model.User;
import com.test.melnyk.internetshop.service.OrderService;
import com.test.melnyk.internetshop.transaction.TransactionManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderDao orderDao, TransactionManager transactionManager) {
        this.orderDao = orderDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void insertNewOrder(Order order) {
        transactionManager.doTransaction(()->orderDao.create(order));
    }

    @Override
    public Order getOrder(User user, Map<Item, Integer> cartItems) {
        List<OrderedItem> orderedItems = new ArrayList<>(cartItems.size());
        for (Map.Entry<Item, Integer> entry : cartItems.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            OrderedItem orderedItem = new OrderedItem(item, quantity);
            orderedItems.add(orderedItem);
        }
        System.out.println(orderedItems.toString());
        Order order = new Order();
        order.setOrderStatus(OrderStatus.FORMED);
        order.setDetails("order has approved");
        order.setLocalDateTime(LocalDateTime.now());
        order.setCustomer(user);
        order.setOrderedItem(orderedItems);
        return order;
    }
}
