package org.example.enums;

public enum Meeting {
    CORE("骨干例会"),
    GENERAL("全员大会");

    private final String name;

    Meeting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
