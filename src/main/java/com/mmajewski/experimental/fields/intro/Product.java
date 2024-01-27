package com.mmajewski.experimental.fields.intro;

abstract class Product {
    protected String name;
    protected int year;
    protected double actualPrice;

    protected Product(String name, int year) {
        this.name = name;
        this.year = year;
    }
}
