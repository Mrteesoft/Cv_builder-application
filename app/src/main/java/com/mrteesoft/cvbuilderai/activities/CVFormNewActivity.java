package com.mrteesoft.cvbuilderai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.utils.Constants;
import com.mrteesoft.cvbuilderai.utils.ProgressManager;

public class CVFormNewActivity extends AppCompatActivity {
    private int templateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_form_sections);

        templateId = getIntent().getIntExtra(Constants.EXTRA_TEMPLATE_ID, 1);
        
        initViews();
        setupClickListeners();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateProgress();
    }

    private void initViews() {
        // Load banner ad
        AdView adView = findViewById(R.id.adViewForm);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        // Update progress
        updateProgress();
    }
    
    private void updateProgress() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView txtProgress = findViewById(R.id.txtProgress);
        
        int progress = ProgressManager.getProgressPercentage(this);
        progressBar.setProgress(progress);
        txtProgress.setText(progress + "%");
    }

    private void setupClickListeners() {
        // Section buttons
        LinearLayout btnPersonalInfo = findViewById(R.id.btnPersonalInfo);
        LinearLayout btnEducation = findViewById(R.id.btnEducation);
        LinearLayout btnWorkExperience = findViewById(R.id.btnWorkExperience);
        LinearLayout btnSkills = findViewById(R.id.btnSkills);
        LinearLayout btnLanguages = findViewById(R.id.btnLanguages);
        LinearLayout btnCertifications = findViewById(R.id.btnCertifications);

        btnPersonalInfo.setOnClickListener(v -> openPersonalInfoScreen());
        btnEducation.setOnClickListener(v -> openEducationScreen());
        btnWorkExperience.setOnClickListener(v -> openWorkExperienceScreen());
        btnSkills.setOnClickListener(v -> openSkillsScreen());
        btnLanguages.setOnClickListener(v -> openLanguagesScreen());
        btnCertifications.setOnClickListener(v -> openCertificationsScreen());

        // Action buttons
        TextView btnSaveDraft = findViewById(R.id.btnSaveDraft);
        TextView btnShare = findViewById(R.id.btnShare);
        TextView btnPreview = findViewById(R.id.btnPreview);

        btnSaveDraft.setOnClickListener(v -> saveDraft());
        btnShare.setOnClickListener(v -> shareCV());
        btnPreview.setOnClickListener(v -> previewCV());
    }



    private void saveDraft() {
        // Save current progress
        int progress = ProgressManager.getProgressPercentage(this);
        getSharedPreferences("cv_draft", MODE_PRIVATE)
            .edit()
            .putInt("progress", progress)
            .putLong("last_saved", System.currentTimeMillis())
            .apply();
        
        Toast.makeText(this, "Draft saved successfully!", Toast.LENGTH_SHORT).show();
    }

    private void openPersonalInfoScreen() {
        Intent intent = new Intent(this, PersonalInfoActivity.class);
        startActivity(intent);
    }

    private void openEducationScreen() {
        Intent intent = new Intent(this, EducationActivity.class);
        startActivity(intent);
    }

    private void openWorkExperienceScreen() {
        Intent intent = new Intent(this, WorkExperienceActivity.class);
        startActivity(intent);
    }

    private void openSkillsScreen() {
        Intent intent = new Intent(this, SkillsActivity.class);
        startActivity(intent);
    }

    private void openLanguagesScreen() {
        Intent intent = new Intent(this, LanguagesActivity.class);
        startActivity(intent);
    }

    private void openCertificationsScreen() {
        Intent intent = new Intent(this, CertificationsActivity.class);
        startActivity(intent);
    }

    private void shareCV() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my CV created with CV Builder!");
        startActivity(Intent.createChooser(shareIntent, "Share CV"));
    }

    private void previewCV() {
        int progress = ProgressManager.getProgressPercentage(this);
        if (progress < 50) {
            Toast.makeText(this, "Please complete at least Personal Info and one other section", Toast.LENGTH_LONG).show();
            return;
        }
        
        Intent intent = new Intent(this, CVPreviewActivity.class);
        intent.putExtra(Constants.EXTRA_TEMPLATE_ID, templateId);
        intent.putExtra("show_save_options", true);
        startActivity(intent);
    }
}