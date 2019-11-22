package com.test.melnyk.internetshop.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ItemFilterBean {

private String[] colors;
private String[]locations;
private String[] types;
private String[] materials;
private int currentPage;
private int itemPerPage;
private String sortByName;
private BigDecimal sortByPrice;

    public ItemFilterBean() {
    }

    public ItemFilterBean(String[] colors, String[] locations, String[] types, String[] materials, int currentPage, int itemPerPage, String sortByName, BigDecimal sortByPrice) {
        this.colors = colors;
        this.locations = locations;
        this.types = types;
        this.materials = materials;
        this.currentPage = currentPage;
        this.itemPerPage = itemPerPage;
        this.sortByName = sortByName;
        this.sortByPrice = sortByPrice;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getMaterials() {
        return materials;
    }

    public void setMaterials(String[] materials) {
        this.materials = materials;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public String getSortByName() {
        return sortByName;
    }

    public void setSortByName(String sortByName) {
        this.sortByName = sortByName;
    }

    public BigDecimal getSortByPrice() {
        return sortByPrice;
    }

    public void setSortByPrice(BigDecimal sortByPrice) {
        this.sortByPrice = sortByPrice;
    }

    @Override
    public String toString() {
        return "ItemFilterBean{" +
                "colors=" + Arrays.toString(colors) +
                ", locations=" + Arrays.toString(locations) +
                ", types=" + Arrays.toString(types) +
                ", materials=" + Arrays.toString(materials) +
                ", currentPage=" + currentPage +
                ", itemPerPage=" + itemPerPage +
                ", sortByName='" + sortByName + '\'' +
                ", sortByPrice=" + sortByPrice +
                '}';
    }
}
