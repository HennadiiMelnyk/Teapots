package com.test.melnyk.internetshop.repository;

import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.OrderedItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserCartRepository {

    private Map<Item, Integer> cart;

    public UserCartRepository() {
        cart = new LinkedHashMap<>();
    }

    public void add(Item item) {
        if (cart.keySet().contains(item)) {
            int count = cart.get(item);
            cart.put(item, ++count);
        } else {
            cart.put(item, 1);
        }
    }

    public void clearCart() {
        cart = new HashMap<>();
    }

    public void removeItem(Item item) {
        cart.remove(item);
    }

    public Map<Item, Integer> getCartItems() {
        return cart;
    }

    public BigDecimal getTotalPriceInCart() {
        final BigDecimal[] result = {BigDecimal.valueOf(0)};
        cart.forEach((key, value) -> {
            BigDecimal currentTotalPrice = key.getPrice()
                    .multiply(BigDecimal.valueOf(value));
            result[0] = result[0].add(currentTotalPrice);
        });
        return result[0];

    }

    public void updateItemQuantity(int id, int value) {
        Item key = getItemById(id);
        cart.put(key, value);
    }

    public void removeItemById(int id) {
        Item item = getItemById(id);
        if (item != null) {
            cart.remove(item);
        }
    }

    private Item getItemById(int id) {
        Item item;
        for (Map.Entry entry : cart.entrySet()) {
            item = (Item) entry.getKey();
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeBatch(List<OrderedItem> orderedItems) {
        for (OrderedItem orderedItem : orderedItems) {
            cart.remove(orderedItem.getItem());
        }
    }
}
