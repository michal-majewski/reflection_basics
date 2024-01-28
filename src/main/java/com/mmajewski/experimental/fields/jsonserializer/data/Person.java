package com.mmajewski.experimental.fields.jsonserializer.data;

public record Person(String name, boolean employed, int age, float salary, Address address, Company job) {
}
