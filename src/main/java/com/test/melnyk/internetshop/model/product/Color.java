package com.test.melnyk.internetshop.model.product;

import java.util.Objects;

public class Color {

    private int id;
    private String color;

    public Color() {
    }

    public Color(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return id == color1.id &&
                color.equals(color1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color);
    }

    @Override
    public String toString() {
        return  color ;
    }
}
