package com.brilliantsoft.sms.utils;

public final class Constants {
    private Constants() {}
    public static final String BASE_URL = "http://10.0.2.2:8085/";
    public static final String API_BASE = BASE_URL + "api/";
    public static final String PREF_NAME = "sms_secure_session";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_REFRESH_TOKEN = "refreshToken";
    public static final String KEY_TOKEN_TYPE = "tokenType";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ROLE = "role";
    public static final String KEY_EXPIRES_AT = "expiresAt";
    public static final String KEY_STUDENT_ID = "studentId";
    public static final String KEY_STUDENT_CODE = "studentCode";
    public static final String KEY_PROGRAM_NAME = "programName";
    public static final int PAGE_SIZE = 20;
    public static final long CONNECT_TIMEOUT = 30;
    public static final long READ_TIMEOUT = 30;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
}
