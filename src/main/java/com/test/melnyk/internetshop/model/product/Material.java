package com.test.melnyk.internetshop.model.product;

import java.util.Objects;

public class Material {
    private int id;
    private String material;


    public Material() {
    }

    public Material(int id, String material) {
        this.id = id;
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material1 = (Material) o;
        return id == material1.id &&
                material.equals(material1.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, material);
    }

    @Override
    public String toString() {
        return material;
    }
}
