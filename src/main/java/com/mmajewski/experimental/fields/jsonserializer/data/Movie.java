package com.mmajewski.experimental.fields.jsonserializer.data;

public record Movie(String name, float rating, String[] categories, Actor[] actors) {
}
