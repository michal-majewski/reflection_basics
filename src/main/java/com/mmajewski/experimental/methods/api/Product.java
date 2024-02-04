package com.mmajewski.experimental.methods.api;

import java.util.Date;

public final class Product {
    private double price;
    private String name;
    private long quantity;
    private Date expirationDate;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

}
