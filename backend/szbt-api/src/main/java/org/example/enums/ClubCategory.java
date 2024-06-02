package org.example.enums;

public enum ClubCategory {

    ACADEMIC("学术类"),
    SPORTS("体育类"),
    ART("艺术类"),
    PUBLIC_SERVICE("公益类"),
    TECHNOLOGY("科技类"),
    OTHER("其它类");

    private final String name;

    ClubCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
