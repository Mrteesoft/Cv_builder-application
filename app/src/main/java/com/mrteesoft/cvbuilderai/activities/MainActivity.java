package com.mrteesoft.cvbuilderai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.utils.Constants;
import com.mrteesoft.cvbuilderai.utils.ProgressManager;
import com.mrteesoft.cvbuilderai.utils.CVDataManager;
import com.mrteesoft.cvbuilderai.utils.AdManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private int userActionCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeAds();
        setupClickListeners();
        updateRecentCV();
    }
    
    private void initializeAds() {
        MobileAds.initialize(this);
        
        // Load banner ad
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        // Load interstitial ad
        loadInterstitialAd();
    }
    
    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-6483271377992812/1699284347", adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(InterstitialAd interstitialAd) {
                    mInterstitialAd = interstitialAd;
                }
                
                @Override
                public void onAdFailedToLoad(com.google.android.gms.ads.LoadAdError loadAdError) {
                    mInterstitialAd = null;
                }
            });
    }

    private void setupClickListeners() {
        // AI Assistant
        LinearLayout btnAIAssistant = findViewById(R.id.btnAIAssistant);
        btnAIAssistant.setOnClickListener(v -> {
            startActivity(new Intent(this, AIAssistantActivity.class));
        });

        // Template clicks
        LinearLayout template1 = findViewById(R.id.template1);
        LinearLayout template2 = findViewById(R.id.template2);
        LinearLayout template3 = findViewById(R.id.template3);
        
        template1.setOnClickListener(v -> openCVForm(1));
        template2.setOnClickListener(v -> openCVForm(2));
        template3.setOnClickListener(v -> openCVForm(3));

        // Action buttons
        TextView btnStartNewCV = findViewById(R.id.btnStartNewCV);
        TextView btnContinueEditing = findViewById(R.id.btnContinueEditing);
        
        btnStartNewCV.setOnClickListener(v -> openCVForm(1));
        btnContinueEditing.setOnClickListener(v -> {
            userActionCount++;
            
            // Show interstitial only if timing guidelines are met
            if (mInterstitialAd != null && AdManager.shouldShowInterstitial(this, userActionCount)) {
                mInterstitialAd.show(this);
                AdManager.recordInterstitialShown(this);
                userActionCount = 0;
                
                mInterstitialAd.setFullScreenContentCallback(new com.google.android.gms.ads.FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Intent intent = new Intent(MainActivity.this, CVFormNewActivity.class);
                        startActivity(intent);
                        loadInterstitialAd();
                    }
                    
                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        Intent intent = new Intent(MainActivity.this, CVFormNewActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                Intent intent = new Intent(this, CVFormNewActivity.class);
                startActivity(intent);
            }
        });
        
        // Recent CV click
        LinearLayout recentCVItem = findViewById(R.id.recentCVItem);
        recentCVItem.setOnClickListener(v -> {
            userActionCount++;
            Intent intent = new Intent(this, CVFormNewActivity.class);
            startActivity(intent);
        });
    }
    
    private void openCVForm(int templateId) {
        userActionCount++;
        
        // Show interstitial only if timing guidelines are met
        if (mInterstitialAd != null && AdManager.shouldShowInterstitial(this, userActionCount)) {
            mInterstitialAd.show(this);
            AdManager.recordInterstitialShown(this);
            userActionCount = 0; // Reset counter after showing ad
            
            mInterstitialAd.setFullScreenContentCallback(new com.google.android.gms.ads.FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    navigateToCVForm(templateId);
                    loadInterstitialAd();
                }
                
                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    navigateToCVForm(templateId);
                    loadInterstitialAd();
                }
            });
        } else {
            navigateToCVForm(templateId);
        }
    }
    
    private void navigateToCVForm(int templateId) {
        Intent intent = new Intent(this, CVFormNewActivity.class);
        intent.putExtra(Constants.EXTRA_TEMPLATE_ID, templateId);
        startActivity(intent);
    }
    
    private void updateRecentCV() {
        TextView txtRecentCVName = findViewById(R.id.txtRecentCVName);
        TextView txtRecentCVDate = findViewById(R.id.txtRecentCVDate);
        TextView txtRecentCVProgress = findViewById(R.id.txtRecentCVProgress);
        LinearLayout recentCVContainer = findViewById(R.id.recentCVContainer);
        
        String name = CVDataManager.getData(this, CVDataManager.KEY_FULL_NAME);
        int progress = ProgressManager.getProgressPercentage(this);
        
        if (!name.isEmpty() || progress > 0) {
            txtRecentCVName.setText(name.isEmpty() ? "My Professional CV" : name + "'s CV");
            txtRecentCVDate.setText("Last edited " + getCurrentDate());
            txtRecentCVProgress.setText(progress + "%");
            recentCVContainer.setVisibility(View.VISIBLE);
        } else {
            recentCVContainer.setVisibility(View.GONE);
        }
    }
    
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd", Locale.getDefault());
        return sdf.format(new Date());
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateRecentCV();
    }
}