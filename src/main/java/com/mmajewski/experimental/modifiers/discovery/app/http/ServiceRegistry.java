package com.mmajewski.experimental.modifiers.discovery.app.http;

import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerClass;
import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {
    @InitializerMethod
    public void registerService() {
        System.out.println("Service successfully registered");
    }
}
