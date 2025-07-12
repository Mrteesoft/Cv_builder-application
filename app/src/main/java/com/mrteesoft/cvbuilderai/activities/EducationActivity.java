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
import com.mrteesoft.cvbuilderai.utils.CVDataManager;

public class EducationActivity extends AppCompatActivity {
    private EditText etSchoolName, etDegree, etFieldOfStudy, etGraduationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etSchoolName = findViewById(R.id.etSchoolName);
        etDegree = findViewById(R.id.etDegree);
        etFieldOfStudy = findViewById(R.id.etFieldOfStudy);
        etGraduationDate = findViewById(R.id.etGraduationDate);
    }

    private void setupClickListeners() {
        ImageView btnBack = findViewById(R.id.btnBack);
        LinearLayout btnSuggestDegree = findViewById(R.id.btnSuggestDegree);
        TextView btnSave = findViewById(R.id.btnSave);

        btnBack.setOnClickListener(v -> onBackPressed());
        btnSuggestDegree.setOnClickListener(v -> suggestDegree());
        btnSave.setOnClickListener(v -> saveEducation());
    }

    private void suggestDegree() {
        String[] degrees = {"Bachelor's Degree", "Master's Degree", "Associate Degree", "Diploma", "Certificate"};
        String suggestion = degrees[(int) (Math.random() * degrees.length)];
        etDegree.setText(suggestion);
        Toast.makeText(this, "Degree suggested!", Toast.LENGTH_SHORT).show();
    }

    private void saveEducation() {
        String school = etSchoolName.getText().toString().trim();
        String degree = etDegree.getText().toString().trim();
        String fieldOfStudy = etFieldOfStudy.getText().toString().trim();
        String graduationDate = etGraduationDate.getText().toString().trim();
        
        if (school.isEmpty()) {
            Toast.makeText(this, "Please enter school name", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (degree.isEmpty()) {
            Toast.makeText(this, "Please enter degree", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Save all data
        CVDataManager.saveData(this, CVDataManager.KEY_SCHOOL_NAME, school);
        CVDataManager.saveData(this, CVDataManager.KEY_DEGREE, degree);
        CVDataManager.saveData(this, CVDataManager.KEY_FIELD_OF_STUDY, fieldOfStudy);
        CVDataManager.saveData(this, CVDataManager.KEY_GRADUATION_DATE, graduationDate);
        
        ProgressManager.markSectionCompleted(this, ProgressManager.SECTION_EDUCATION);
        Toast.makeText(this, "Education saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}