package com.test.melnyk.internetshop.model;

public class OrderedItem {

    private Item item;

    private int quantity;


    public OrderedItem() {
    }

    public OrderedItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
