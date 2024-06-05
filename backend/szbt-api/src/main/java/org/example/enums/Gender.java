package org.example.enums;

public enum Gender {
    WOMAN("女", 0),
    MAN("男", 1);

    private final String name;

    private final int code;

    Gender(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}