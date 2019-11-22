package com.test.melnyk.internetshop.service;

import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.OrderedItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CartService {

    void addItemToCart(Item item);

    void removeItemFromCart(Item item);

    void removeItemById(int id);

    void clearCart();

    void updateItemQuantity(int id, int newValue);

    BigDecimal getTotal();

    Map<Item, Integer> getCartItems();

    void removeBatch(List<OrderedItem> orderedItems);
}
