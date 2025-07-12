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

public class CertificationsActivity extends AppCompatActivity {
    private EditText etCertificationName, etIssuingOrganization, etIssueDate, etExpiryDate, etCredentialId, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etCertificationName = findViewById(R.id.etCertificationName);
        etIssuingOrganization = findViewById(R.id.etIssuingOrganization);
        etIssueDate = findViewById(R.id.etIssueDate);
        etExpiryDate = findViewById(R.id.etExpiryDate);
        etCredentialId = findViewById(R.id.etCredentialId);
        etDescription = findViewById(R.id.etDescription);
    }

    private void setupClickListeners() {
        ImageView btnBack = findViewById(R.id.btnBack);
        LinearLayout btnSuggestCertification = findViewById(R.id.btnSuggestCertification);
        TextView btnSave = findViewById(R.id.btnSave);

        btnBack.setOnClickListener(v -> onBackPressed());
        btnSuggestCertification.setOnClickListener(v -> suggestCertification());
        btnSave.setOnClickListener(v -> saveCertification());
    }

    private void suggestCertification() {
        String[] certifications = {
            "AWS Certified Solutions Architect",
            "Google Analytics Certified",
            "Microsoft Azure Fundamentals",
            "Certified Scrum Master",
            "PMP - Project Management Professional",
            "CompTIA Security+"
        };
        
        String[] organizations = {
            "Amazon Web Services",
            "Google",
            "Microsoft",
            "Scrum Alliance",
            "Project Management Institute",
            "CompTIA"
        };
        
        int index = (int) (Math.random() * certifications.length);
        etCertificationName.setText(certifications[index]);
        etIssuingOrganization.setText(organizations[index]);
        
        Toast.makeText(this, "Certification suggested!", Toast.LENGTH_SHORT).show();
    }

    private void saveCertification() {
        String certName = etCertificationName.getText().toString().trim();
        String organization = etIssuingOrganization.getText().toString().trim();
        
        if (certName.isEmpty()) {
            Toast.makeText(this, "Please enter certification name", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (organization.isEmpty()) {
            Toast.makeText(this, "Please enter issuing organization", Toast.LENGTH_SHORT).show();
            return;
        }
        
        ProgressManager.markSectionCompleted(this, ProgressManager.SECTION_CERTIFICATIONS);
        Toast.makeText(this, "Certification saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}