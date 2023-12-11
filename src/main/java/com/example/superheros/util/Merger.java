package com.example.superheros.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Merger<T> {

    public T merge(T original, T updated) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (!original.getClass().equals(updated.getClass())) {
            throw new IllegalArgumentException("Original and updated objects must be of the same class");
        }

        T result = (T) original.getClass().getDeclaredConstructor().newInstance();

        Field[] fields = original.getClass().getDeclaredFields();

        for (Field field : fields) {
            // Set the field to be accessible, as private fields may not be accessible by default
            field.setAccessible(true);

            try {
                
                if (field.get(updated) != null) {
                    field.set(result, field.get(updated));
                } else {
                    field.set(result, field.get(original));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
}
