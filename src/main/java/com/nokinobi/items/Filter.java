package com.nokinobi.items;

public class Filter {

    private String name;
    private String priceMax;
    private String priceMin;
    private String capacity;

    public Filter(String name, String priceMax, String priceMin, String capacity) {
        this.name = name;
        this.priceMax = priceMax;
        this.priceMin = priceMin;
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceMax(String priceMax) {
        this.priceMax = priceMax;
    }

    public void setPriceMin(String priceMin) {
        this.priceMin = priceMin;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getPriceMax() {
        return priceMax;
    }

    public String getPriceMin() {
        return priceMin;
    }

    public String getCapacity() {
        return capacity;
    }
}
