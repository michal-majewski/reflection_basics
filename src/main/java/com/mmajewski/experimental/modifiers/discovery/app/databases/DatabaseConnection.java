package com.mmajewski.experimental.modifiers.discovery.app.databases;

import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerClass;
import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerMethod;

@InitializerClass
public class DatabaseConnection {
    @InitializerMethod
    public void connectToDatabase1() {
        System.out.println("Connecting to database 1");
    }

    @InitializerMethod
    public void connectToDatabase2() {
        System.out.println("Connecting to database 2");
    }
}
