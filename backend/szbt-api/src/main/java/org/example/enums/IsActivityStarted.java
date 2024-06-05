package org.example.enums;

public enum IsActivityStarted {
    NO_STARTED("未开始", 0),
    STARTING("进行中", 1),

    ENDED("已结束",2);

    private final String name;

    private final  int code;

    IsActivityStarted(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {return code;}
}
