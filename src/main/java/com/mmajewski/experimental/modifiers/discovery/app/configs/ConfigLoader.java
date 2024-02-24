package com.mmajewski.experimental.modifiers.discovery.app.configs;

import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerClass;
import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerMethod;

@InitializerClass
public class ConfigLoader {
    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}
