package org.example.enums;

public enum MainCampus {
    NORTH("北校区"),
    SOUTH("南校区"),
    EAST("东校区"),
    ZHUHAI("珠海校区"),
    SHENZHEN("深圳校区");

    private final String name;
    MainCampus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
