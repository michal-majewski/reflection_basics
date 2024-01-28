package com.mmajewski.experimental.fields.jsonserializer.jsonwriter;

import java.lang.reflect.Field;

final class JsonWriter {
    JsonWriter() {
    }

    public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        startJson(sb);
        formatEachField(instance, indentSize, fields, sb);
        endJson(indentSize, sb);

        return sb.toString();
    }

    private static void formatEachField(Object instance, int indentSize, Field[] fields, StringBuilder sb) throws IllegalAccessException {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.isSynthetic()) {
                continue;
            }

            sb.append(indent(indentSize + 1));
            sb.append(formatStringValue(field.getName()));
            sb.append(":");

            Class<?> type = field.getType();
            if (type.isPrimitive()) {
                sb.append(formatPrimitiveValue(field, instance));
            } else if (type.equals(String.class)) {
                sb.append(formatStringValue(field.get(instance).toString()));
            } else {
                sb.append(objectToJson(field.get(instance), indentSize + 1));
            }

            if (i != fields.length - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
    }

    private static void endJson(int indentSize, StringBuilder sb) {
        sb.append(indent(indentSize));
        sb.append("}");
    }

    private static void startJson(StringBuilder sb) {
        sb.append("{");
        sb.append("\n");
    }

    private static String indent(int indentSize) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentSize; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalAccessException {
        Class<?> type = field.getType();
        if (type.equals(boolean.class) || type.equals(int.class) || type.equals(long.class) || type.equals(short.class)) {
            return field.get(parentInstance).toString();
        } else if (type.equals(double.class) || type.equals(float.class)) {
            return String.format("%.02f", field.get(parentInstance));
        }

        throw new RuntimeException(String.format("Type: %s is unsupported", type.getName()));
    }

    private static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }
}
