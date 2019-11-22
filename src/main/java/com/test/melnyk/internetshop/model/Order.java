package com.test.melnyk.internetshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private int id;

    private OrderStatus orderStatus;

    private String details;

    private LocalDateTime localDateTime;

    private User customer;

    private List<OrderedItem> orderedItem;

    public Order() {
    }

    public Order(int id, OrderStatus orderStatus, String details, LocalDateTime localDateTime, User customer, List<OrderedItem> orderedItem) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.details = details;
        this.localDateTime = localDateTime;
        this.customer = customer;
        this.orderedItem = orderedItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderedItem> getOrderedItem() {
        return orderedItem;
    }

    public void setOrderedItem(List<OrderedItem> orderedItem) {
        this.orderedItem = orderedItem;
    }
}
