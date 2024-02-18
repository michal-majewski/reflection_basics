package com.mmajewski.experimental.methods.test;

import com.mmajewski.experimental.methods.api.ClothingProduct;
import com.mmajewski.experimental.methods.api.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

class ProductTest {
    public static void main(String[] args) {
        testGetters(ClothingProduct.class);
        testSetters(ClothingProduct.class);
    }

    static void testSetters(Class<?> dataClass) {
        List<Field> fields = allFields(dataClass);

        for (Field field : fields) {
            String setterName = "set" + capitalizeFirstLetter(field.getName());

            Method setterMethod = null;
            try {
                setterMethod = dataClass.getMethod(setterName, field.getType());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(format("Setter: %s not found", setterName));
            }

            if (!setterMethod.getReturnType().equals(void.class)) {
               throw new IllegalStateException(format("Setter method: %s has to return void", setterName));
            }
        }
    }

    static void testGetters(Class<?> dataClass) {
        List<Field> fields = allFields(dataClass);

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

    private static List<Field> allFields(Class<?> clazz) {
        if (clazz == null || clazz.equals(Object.class)) {
           return Collections.emptyList();
        }

        Field[] currentClassFields = clazz.getDeclaredFields();
        List<Field> inheritedFields = allFields(clazz.getSuperclass());

        ArrayList<Field> allFields = new ArrayList<>();

        allFields.addAll(List.of(currentClassFields));
        allFields.addAll(inheritedFields);

        return allFields;
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
