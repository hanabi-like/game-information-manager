package com.example.repository.util;

public final class RedisKey {
    private static final String PREFIX = "game-information-manager";

    private RedisKey() {
    }

    public static String userKey(String username) {
        return PREFIX + ":" + username;
    }

    public static String gameKey(String username, String gameName) {
        return PREFIX + ":" + username + ":" + gameName;
    }

    public static String gameCharacterKey(String username, String gameName, String characterName) {
        return PREFIX + ":" + username + ":" + gameName + ":" + characterName;
    }

    public static String gamePattern(String username) {
        return PREFIX + ":" + username + ":*";
    }

    public static String gameCharacterPattern(String username, String gameName) {
        return PREFIX + ":" + username + ":" + gameName + ":*";
    }
}