package com.mrteesoft.cvbuilderai.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class SharedPrefsManager {
    private static final String PREF_NAME = "faith_prefs";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SharedPrefsManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public String getUserId() {
        String userId = prefs.getString(Constants.PREF_USER_ID, null);
        if (userId == null) {
            userId = UUID.randomUUID().toString();
            editor.putString(Constants.PREF_USER_ID, userId).apply();
        }
        return userId;
    }

    public boolean isFirstLaunch() {
        return prefs.getBoolean(Constants.PREF_FIRST_LAUNCH, true);
    }

    public void setFirstLaunchComplete() {
        editor.putBoolean(Constants.PREF_FIRST_LAUNCH, false).apply();
    }
}