package com.example.jwt_study.global.util;

public class RedisKeyGenerator {

    private static final String ROLE_PREFIX = "role:";
    private static final String JTI_PREFIX = "auth:jti:";
    private static final String USER_PREFIX = "auth:user:";

    public static String getRolePrefix(String key) {
        return ROLE_PREFIX + key + ":apis";
    }

    public static String getUserAtPrefix(String key) {
        return USER_PREFIX + key + ":at";
    }

    public static String getUserRtPrefix(String key) {
        return USER_PREFIX + key + ":rt";
    }

    public static String getLogoutPrefix(String key) {
        return "logout" + key;
    }
}
