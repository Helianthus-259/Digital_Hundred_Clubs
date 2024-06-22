package org.example.util;

public class RedisKeyBuilder {
    public static String generateEmailKey(String email) {
        return "email_"+email;
    }

    public static String generateClubKey(Integer clubId) {
        return "club_"+clubId.toString();
    }

    public static String generateClubInfosListKey() {
        return "clubInfosListKey";
    }
}
