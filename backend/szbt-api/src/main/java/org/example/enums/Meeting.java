package org.example.enums;

public enum Meeting {
    CORE("骨干例会", 0),
    GENERAL("全员大会", 1),

    OTHER("其它",2);

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
