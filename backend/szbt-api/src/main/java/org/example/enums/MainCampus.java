package org.example.enums;

public enum MainCampus {
    NORTH("广州校区北校园", 0),
    SOUTH("广州校区南校园", 1),
    EAST("广州校区东校园", 2),
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
