package com.test.melnyk.internetshop.service.impl;

import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.OrderedItem;
import com.test.melnyk.internetshop.repository.UserCartRepository;
import com.test.melnyk.internetshop.service.CartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private UserCartRepository userCartRepository;

    public CartServiceImpl(UserCartRepository userCartRepository) {
        this.userCartRepository = userCartRepository;
    }

    @Override
    public void addItemToCart(Item item) {
        userCartRepository.add(item);
    }

    @Override
    public void removeItemFromCart(Item item) {
        userCartRepository.removeItem(item);
    }

    @Override
    public void removeItemById(int id) {
        userCartRepository.removeItemById(id);
    }

    @Override
    public void clearCart() {
        userCartRepository.clearCart();
    }

    @Override
    public void updateItemQuantity(int id, int newValue) {
        userCartRepository.updateItemQuantity(id, newValue);
    }

    @Override
    public BigDecimal getTotal() {
        return userCartRepository.getTotalPriceInCart();
    }

    @Override
    public Map<Item, Integer> getCartItems() {
        return userCartRepository.getCartItems();
    }

    @Override
    public void removeBatch(List<OrderedItem> orderedItems) {
        userCartRepository.removeBatch(orderedItems);
    }
}
