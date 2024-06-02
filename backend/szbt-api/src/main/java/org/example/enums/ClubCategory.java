package org.example.enums;

public enum ClubCategory {

    ACADEMIC("学术类", 1),
    SPORTS("体育类", 2),
    ART("艺术类", 3),
    PUBLIC_SERVICE("公益类", 4),
    TECHNOLOGY("科技类", 5),
    OTHER("其它类", 6);

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
