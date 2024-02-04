package com.mmajewski.experimental.methods.test;

import com.mmajewski.experimental.methods.api.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

class ProductTest {
    public static void main(String[] args) {
        testGetters(Product.class);
    }

    static void testGetters(Class<?> dataClass) {
        Field[] fields = dataClass.getDeclaredFields();

        Map<String, Method> methodNameToMethod = mapMethodNameToMethod(dataClass);

        for (Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter(field.getName());

            if (!methodNameToMethod.containsKey(getterName)) {
                throw new IllegalStateException(
                        format("Field: %s doesn't have a getter method", field.getName())
                );
            }

            Method getter = methodNameToMethod.get(getterName);

            if (!getter.getReturnType().equals(field.getType())) {
                throw new IllegalStateException(
                        format("Getter method: %s() has returned type %s but expected %s",
                                getter.getName(),
                                getter.getReturnType().getTypeName(),
                                field.getType().getTypeName())
                );
            }

            if (getter.getParameterCount() > 0) {
                throw new IllegalStateException(
                  format("Getter: %s has %d arguments, where expected 0 arguments",
                          getterName,
                          getter.getParameterCount())
                );
            }
        }
    }

    private static String capitalizeFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
    }

    private static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass) {
        Method[] methods = dataClass.getMethods();

        HashMap<String, Method> nameToMethod = new HashMap<>();

        for (Method method : methods) {
            nameToMethod.put(method.getName(), method);
        }

        return nameToMethod;
    }
}
