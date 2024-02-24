package com.mmajewski.experimental.modifiers.discovery.app;

import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerClass;
import com.mmajewski.experimental.modifiers.discovery.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {
    @InitializerMethod
    public void startAutoSavingThreads() {
        System.out.println("Start automatic data saving to disk");
    }
}
