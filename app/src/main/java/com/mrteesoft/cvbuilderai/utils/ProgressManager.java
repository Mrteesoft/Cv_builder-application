package com.mrteesoft.cvbuilderai.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ProgressManager {
    private static final String PREF_NAME = "cv_progress";
    private static final String KEY_PERSONAL_INFO = "personal_info_completed";
    private static final String KEY_EDUCATION = "education_completed";
    private static final String KEY_WORK_EXPERIENCE = "work_experience_completed";
    private static final String KEY_SKILLS = "skills_completed";
    private static final String KEY_LANGUAGES = "languages_completed";
    private static final String KEY_CERTIFICATIONS = "certifications_completed";
    
    public static void markSectionCompleted(Context context, String section) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(section, true).apply();
    }
    
    public static boolean isSectionCompleted(Context context, String section) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(section, false);
    }
    
    public static int getProgressPercentage(Context context) {
        int completedSections = 0;
        int totalSections = 6;
        
        if (isSectionCompleted(context, KEY_PERSONAL_INFO)) completedSections++;
        if (isSectionCompleted(context, KEY_EDUCATION)) completedSections++;
        if (isSectionCompleted(context, KEY_WORK_EXPERIENCE)) completedSections++;
        if (isSectionCompleted(context, KEY_SKILLS)) completedSections++;
        if (isSectionCompleted(context, KEY_LANGUAGES)) completedSections++;
        if (isSectionCompleted(context, KEY_CERTIFICATIONS)) completedSections++;
        
        return (completedSections * 100) / totalSections;
    }
    
    public static final String SECTION_PERSONAL_INFO = KEY_PERSONAL_INFO;
    public static final String SECTION_EDUCATION = KEY_EDUCATION;
    public static final String SECTION_WORK_EXPERIENCE = KEY_WORK_EXPERIENCE;
    public static final String SECTION_SKILLS = KEY_SKILLS;
    public static final String SECTION_LANGUAGES = KEY_LANGUAGES;
    public static final String SECTION_CERTIFICATIONS = KEY_CERTIFICATIONS;
}