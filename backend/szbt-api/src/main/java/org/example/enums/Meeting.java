package org.example.enums;

public enum Meeting {
    CORE("骨干例会", 1),
    GENERAL("全员大会", 2),

    OTHER("其它",3);

    private final String name;

    private final  int code;

    Meeting(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {return code;}
}
