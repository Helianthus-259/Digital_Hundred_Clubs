package org.example.util;

public class EnumHelper {
    public static <T extends Enum<T>> T getEnumByValue(int value, Class<T> enumClass) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (((Enum<?>) enumValue).ordinal() == value) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Invalid enum value: " + value);
    }
}
