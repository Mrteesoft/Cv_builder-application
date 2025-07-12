package com.mrteesoft.cvbuilderai.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CVDataManager {
    private static final String PREF_NAME = "cv_data";
    
    // Personal Info keys
    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_LINKEDIN = "linkedin";
    public static final String KEY_ADDRESS = "address";
    
    // Education keys
    public static final String KEY_SCHOOL_NAME = "school_name";
    public static final String KEY_DEGREE = "degree";
    public static final String KEY_FIELD_OF_STUDY = "field_of_study";
    public static final String KEY_GRADUATION_DATE = "graduation_date";
    
    // Work Experience keys
    public static final String KEY_JOB_TITLE = "job_title";
    public static final String KEY_COMPANY_NAME = "company_name";
    public static final String KEY_START_DATE = "start_date";
    public static final String KEY_END_DATE = "end_date";
    public static final String KEY_RESPONSIBILITIES = "responsibilities";
    
    public static void saveData(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(key, value).apply();
    }
    
    public static String getData(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }
    
    public static boolean hasData(Context context, String... keys) {
        for (String key : keys) {
            if (getData(context, key).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}