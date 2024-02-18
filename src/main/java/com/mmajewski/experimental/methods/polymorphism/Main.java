package com.mmajewski.experimental.methods.polymorphism;

import com.mmajewski.experimental.methods.polymorphism.database.DatabaseClient;
import com.mmajewski.experimental.methods.polymorphism.http.HttpClient;
import com.mmajewski.experimental.methods.polymorphism.logging.FileLogger;
import com.mmajewski.experimental.methods.polymorphism.udp.UdpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Throwable {
        DatabaseClient databaseClient = new DatabaseClient();
        HttpClient httpClient1 = new HttpClient("123.456.789.0");
        HttpClient httpClient2 = new HttpClient("11.33.55.0");
        FileLogger fileLogger = new FileLogger();
        UdpClient udpClient = new UdpClient();

        String requestBody = "request data";

        List<Class<?>> methodParameterTypes = Arrays.asList(String.class);

        Map<Object, Method> requestExecutors = groupExecutors(
                Arrays.asList(databaseClient, httpClient1, httpClient2, fileLogger, udpClient),
                methodParameterTypes
        );

        executeAll(requestExecutors, requestBody);
    }

    static void executeAll(Map<Object, Method> requestExecutors, String requestBody) throws Throwable {
        try {
            for (Map.Entry<Object, Method> requestExecutorEntry : requestExecutors.entrySet()) {
                Object requestExecutor = requestExecutorEntry.getKey();
                Method method = requestExecutorEntry.getValue();

                Boolean result = (Boolean) method.invoke(requestExecutor, requestBody);

                if (result != null && result.equals(Boolean.FALSE)) {
                    System.err.println("Received negative result. Aborting...");
                    return;
                }
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    static Map<Object, Method> groupExecutors(List<Object> requestExecutors, List<Class<?>> methodParameterTypes) {
        Map<Object, Method> instanceToMethod = new HashMap<>();

        for (Object requestExecutor : requestExecutors) {
            Method[] allMethods = requestExecutor.getClass().getDeclaredMethods();

            for (Method method : allMethods) {
                if (Arrays.asList(method.getParameterTypes()).equals(methodParameterTypes)) {
                   instanceToMethod.put(requestExecutor, method);
                }
            }
        }
        return instanceToMethod;
    }
}
