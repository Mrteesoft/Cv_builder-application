package com.mrteesoft.cvbuilderai.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mrteesoft.cvbuilderai.utils.ProgressManager;
import com.mrteesoft.cvbuilderai.utils.CVDataManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mrteesoft.cvbuilderai.R;

public class PersonalInfoActivity extends AppCompatActivity {
    private EditText etFullName, etEmail, etPhone, etLinkedIn, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        initViews();
        setupClickListeners();
        loadAds();
    }

    private void initViews() {
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etLinkedIn = findViewById(R.id.etLinkedIn);
        etAddress = findViewById(R.id.etAddress);
    }

    private void setupClickListeners() {
        ImageView btnBack = findViewById(R.id.btnBack);
        LinearLayout btnSuggestEmail = findViewById(R.id.btnSuggestEmail);
        LinearLayout btnSuggestPhone = findViewById(R.id.btnSuggestPhone);
        TextView btnSave = findViewById(R.id.btnSave);

        btnBack.setOnClickListener(v -> onBackPressed());
        btnSuggestEmail.setOnClickListener(v -> suggestEmail());
        btnSuggestPhone.setOnClickListener(v -> suggestPhone());
        btnSave.setOnClickListener(v -> savePersonalInfo());
    }

    private void suggestEmail() {
        String name = etFullName.getText().toString().trim();
        if (!name.isEmpty()) {
            String suggestion = name.toLowerCase().replace(" ", ".") + "@email.com";
            etEmail.setText(suggestion);
            Toast.makeText(this, "Email suggested!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter your name first", Toast.LENGTH_SHORT).show();
        }
    }

    private void suggestPhone() {
        etPhone.setText("+1 (555) 123-4567");
        Toast.makeText(this, "Sample phone format suggested", Toast.LENGTH_SHORT).show();
    }

    private void savePersonalInfo() {
        String name = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String linkedin = etLinkedIn.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Save all data
        CVDataManager.saveData(this, CVDataManager.KEY_FULL_NAME, name);
        CVDataManager.saveData(this, CVDataManager.KEY_EMAIL, email);
        CVDataManager.saveData(this, CVDataManager.KEY_PHONE, phone);
        CVDataManager.saveData(this, CVDataManager.KEY_LINKEDIN, linkedin);
        CVDataManager.saveData(this, CVDataManager.KEY_ADDRESS, address);
        
        // Mark section as completed
        ProgressManager.markSectionCompleted(this, ProgressManager.SECTION_PERSONAL_INFO);
        Toast.makeText(this, "Personal information saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
    
    private void loadAds() {
        AdView adView = findViewById(R.id.adViewPersonal);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}