package org.example.enums;

public enum ClubCategory {

    ACADEMIC("学术类", 0),
    SPORTS("体育类", 1),
    ART("艺术类", 2),
    PUBLIC_SERVICE("公益类", 3),
    TECHNOLOGY("科技类", 4),
    OTHER("其它类", 5);

    private final String name;

    private final  int code;

    ClubCategory(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {return code;}
}
