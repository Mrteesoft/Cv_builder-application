package com.mrteesoft.cvbuilderai.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdManager {
    
    // Production AdMob IDs
    public static final String BANNER_AD_UNIT_ID = "ca-app-pub-6483271377992812/1525407412";
    public static final String INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-6483271377992812/1699284347";
    
    private static boolean isInitialized = false;
    
    public static void initialize(Context context) {
        if (!isInitialized) {
            MobileAds.initialize(context);
            isInitialized = true;
        }
    }
    
    public static void loadBannerAd(AdView adView) {
        if (adView != null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
    }
    
    public static void loadInterstitialAd(Activity activity, InterstitialAdLoadCallback callback) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, INTERSTITIAL_AD_UNIT_ID, adRequest, callback);
    }
    
    private static final long MIN_INTERVAL_MS = 60000; // 1 minute minimum
    private static final String PREF_LAST_AD_TIME = "last_interstitial_time";
    
    public static boolean shouldShowInterstitial(Context context, int userActionCount) {
        SharedPreferences prefs = context.getSharedPreferences("ad_timing", Context.MODE_PRIVATE);
        long lastAdTime = prefs.getLong(PREF_LAST_AD_TIME, 0);
        long currentTime = System.currentTimeMillis();
        
        // Must wait at least 1 minute between ads AND have 3+ actions
        return (currentTime - lastAdTime) >= MIN_INTERVAL_MS && userActionCount >= 3;
    }
    
    public static void recordInterstitialShown(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("ad_timing", Context.MODE_PRIVATE);
        prefs.edit().putLong(PREF_LAST_AD_TIME, System.currentTimeMillis()).apply();
    }
}