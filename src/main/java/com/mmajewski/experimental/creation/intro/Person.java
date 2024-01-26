package com.mmajewski.experimental.creation.intro;

class Person {
    private final Address address;
    private final String name;
    private final int age;

    Person() {
        this.name = "anonymous";
        this.age = 0;
        this.address = null;
    }

    Person(String name) {
        this.name = name;
        this.age = 0;
        this.address = null;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.address = null;
    }

    Person(Address address, String name, int age) {
        this.address = address;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "address=" + address +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
