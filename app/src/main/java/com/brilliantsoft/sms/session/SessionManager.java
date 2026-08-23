package com.brilliantsoft.sms.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.brilliantsoft.sms.model.LoginResponse;
import com.brilliantsoft.sms.utils.Constants;

public class SessionManager {
    private static SessionManager instance;
    private final SharedPreferences prefs;

    private SessionManager(Context ctx) {
        SharedPreferences tmp;
        try {
            MasterKey masterKey = new MasterKey.Builder(ctx, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            tmp = EncryptedSharedPreferences.create(
                    ctx,
                    Constants.PREF_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception e) {
            tmp = ctx.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        }
        prefs = tmp;
    }

    public static synchronized SessionManager getInstance(Context ctx) {
        if (instance == null) instance = new SessionManager(ctx.getApplicationContext());
        return instance;
    }

    public void saveLogin(LoginResponse r) {
        long expiresAt = System.currentTimeMillis() + (r.getExpiresIn() * 1000L);
        SharedPreferences.Editor e = prefs.edit();
        e.putString(Constants.KEY_TOKEN, r.getToken());
        e.putString(Constants.KEY_REFRESH_TOKEN, r.getRefreshToken());
        e.putString(Constants.KEY_TOKEN_TYPE, r.getTokenType() != null ? r.getTokenType() : "Bearer");
        if (r.getId() != null) e.putLong(Constants.KEY_USER_ID, r.getId());
        e.putString(Constants.KEY_USERNAME, r.getUsername());
        e.putString(Constants.KEY_EMAIL, r.getEmail());
        e.putString(Constants.KEY_ROLE, r.getRole() != null ? r.getRole().name() : null);
        e.putLong(Constants.KEY_EXPIRES_AT, expiresAt);
        e.apply();
    }

    public void saveStudentInfo(Long studentId, String studentCode, String programName) {
        SharedPreferences.Editor e = prefs.edit();
        if (studentId != null) e.putLong(Constants.KEY_STUDENT_ID, studentId);
        e.putString(Constants.KEY_STUDENT_CODE, studentCode);
        e.putString(Constants.KEY_PROGRAM_NAME, programName);
        e.apply();
    }

    public String getToken() { return prefs.getString(Constants.KEY_TOKEN, null); }
    public String getRefreshToken() { return prefs.getString(Constants.KEY_REFRESH_TOKEN, null); }
    public String getTokenType() { return prefs.getString(Constants.KEY_TOKEN_TYPE, "Bearer"); }
    public String getAuthHeader() {
        String t = getToken();
        if (t == null) return null;
        String type = getTokenType();
        return type + " " + t;
    }
    public long getUserId() { return prefs.getLong(Constants.KEY_USER_ID, -1L); }
    public String getUsername() { return prefs.getString(Constants.KEY_USERNAME, null); }
    public String getEmail() { return prefs.getString(Constants.KEY_EMAIL, null); }
    public String getRole() { return prefs.getString(Constants.KEY_ROLE, null); }
    public long getStudentId() { return prefs.getLong(Constants.KEY_STUDENT_ID, -1L); }
    public long getExpiresAt() { return prefs.getLong(Constants.KEY_EXPIRES_AT, 0L); }
    public boolean isLoggedIn() {
        String t = getToken();
        if (t == null || t.isEmpty()) return false;
        long exp = getExpiresAt();
        return exp == 0L || System.currentTimeMillis() < exp;
    }
    public boolean isStudent() { return "STUDENT".equalsIgnoreCase(getRole()); }
    public void clear() { prefs.edit().clear().apply(); }
    public void logout(Context ctx) {
        clear();
        Intent i = new Intent(ctx, com.brilliantsoft.sms.ui.auth.LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ctx.startActivity(i);
    }
}
