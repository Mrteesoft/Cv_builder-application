package com.mrteesoft.cvbuilderai.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.utils.ProgressManager;

public class SkillsActivity extends AppCompatActivity {
    private EditText etTechnicalSkills, etSoftSkills, etCertifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etTechnicalSkills = findViewById(R.id.etTechnicalSkills);
        etSoftSkills = findViewById(R.id.etSoftSkills);
        etCertifications = findViewById(R.id.etCertifications);
    }

    private void setupClickListeners() {
        ImageView btnBack = findViewById(R.id.btnBack);
        LinearLayout btnSuggestTechnical = findViewById(R.id.btnSuggestTechnical);
        LinearLayout btnSuggestSoft = findViewById(R.id.btnSuggestSoft);
        TextView btnSave = findViewById(R.id.btnSave);

        btnBack.setOnClickListener(v -> onBackPressed());
        btnSuggestTechnical.setOnClickListener(v -> suggestTechnicalSkills());
        btnSuggestSoft.setOnClickListener(v -> suggestSoftSkills());
        btnSave.setOnClickListener(v -> saveSkills());
    }

    private void suggestTechnicalSkills() {
        String[] skills = {"Java, Python, JavaScript", "React, Node.js, MongoDB", "AWS, Docker, Kubernetes", "SQL, MySQL, PostgreSQL"};
        String suggestion = skills[(int) (Math.random() * skills.length)];
        etTechnicalSkills.setText(suggestion);
        Toast.makeText(this, "Technical skills suggested!", Toast.LENGTH_SHORT).show();
    }

    private void suggestSoftSkills() {
        String[] skills = {"Communication, Leadership", "Problem Solving, Teamwork", "Time Management, Adaptability", "Critical Thinking, Creativity"};
        String suggestion = skills[(int) (Math.random() * skills.length)];
        etSoftSkills.setText(suggestion);
        Toast.makeText(this, "Soft skills suggested!", Toast.LENGTH_SHORT).show();
    }

    private void saveSkills() {
        ProgressManager.markSectionCompleted(this, ProgressManager.SECTION_SKILLS);
        Toast.makeText(this, "Skills saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}