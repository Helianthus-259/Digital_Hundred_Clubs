package org.example.enums;

public enum Position {
    PRESIDENT("社长"),
    VICE_PRESIDENT("副社长"),
    OFFICER("干部"),
    MEMBER("普通成员");

    private final String name;

    Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
