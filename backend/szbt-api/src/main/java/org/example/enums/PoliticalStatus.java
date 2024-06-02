package org.example.enums;

public enum PoliticalStatus {
    CITIZEN("群众"),
    COMMUNIST_YOUTH_LEAGUE_MEMBER("共青团员"),
    PROBATIONARY_PARTY_MEMBER("预备党员"),
    PARTY_MEMBER("党员");

    private final String name;

    PoliticalStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
