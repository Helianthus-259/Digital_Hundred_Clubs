package org.example.enums;

import java.util.List;

public class EnumWrapper {
    private List<EnumData> positions;
    private List<EnumData> mainCampuses;
    private List<EnumData> clubCategories;
    private List<EnumData> meetings;

    public List<EnumData> getPositions() {
        return positions;
    }

    public void setPositions(List<EnumData> positions) {
        this.positions = positions;
    }

    public List<EnumData> getMainCampuses() {
        return mainCampuses;
    }

    public void setMainCampuses(List<EnumData> mainCampuses) {
        this.mainCampuses = mainCampuses;
    }

    public List<EnumData> getClubCategories() {
        return clubCategories;
    }

    public void setClubCategories(List<EnumData> clubCategories) {
        this.clubCategories = clubCategories;
    }

    public List<EnumData> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<EnumData> meetings) {
        this.meetings = meetings;
    }

    public static class EnumData {
        private String name;
        private int code;

        public EnumData(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
