package com.test.melnyk.internetshop.model;

import com.test.melnyk.internetshop.model.product.Color;
import com.test.melnyk.internetshop.model.product.Location;
import com.test.melnyk.internetshop.model.product.Material;
import com.test.melnyk.internetshop.model.product.Type;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private BigDecimal price;
    private int weight;
    private int age;
    private Location location;
    private Material material;
    private Type type;
    private Color color;

    public Item() {
    }

    public Item(int id, String name, BigDecimal price, int weight, int age, Location location, Material material, Type type, Color color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.age = age;
        this.location = location;
        this.material = material;
        this.type = type;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                weight == item.weight &&
                age == item.age &&
                name.equals(item.name) &&
                price.equals(item.price) &&
                location.equals(item.location) &&
                material.equals(item.material) &&
                type.equals(item.type) &&
                color.equals(item.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, weight, age, location, material, type, color);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", age=" + age +
                ", location=" + location +
                ", material=" + material +
                ", type=" + type +
                ", color=" + color +
                '}';
    }
}
