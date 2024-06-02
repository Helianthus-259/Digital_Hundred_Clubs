package org.example.enums;

public enum Position {
    PRESIDENT("社长", 0),
    VICE_PRESIDENT("副社长", 1),
    OFFICER("干部", 2),
    MEMBER("普通成员", 3);

    private final String name;

    private final  int code;

    Position(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {return code;}
}
