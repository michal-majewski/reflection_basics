package com.mmajewski.experimental.objectcreation;

class Address {
    private String street;
    private int number;

    Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
