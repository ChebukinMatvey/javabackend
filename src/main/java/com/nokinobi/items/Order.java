package com.nokinobi.items;

public class Order {
    private String name;
    private int Capacity;
    private int price;
    private String login;
    private String address;
    private String email;


    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return Capacity;
    }

    public int getPrice() {
        return price;
    }

    public String getLogin() {
        return login;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
