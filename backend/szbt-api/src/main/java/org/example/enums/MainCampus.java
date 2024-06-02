package org.example.enums;

public enum MainCampus {
    NORTH("北校区", 0),
    SOUTH("南校区", 1),
    EAST("东校区", 2),
    ZHUHAI("珠海校区", 3),
    SHENZHEN("深圳校区", 4);

    private final String name;
    private final  int code;
    MainCampus(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {return code;}
}
