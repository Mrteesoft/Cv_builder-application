package com.mrteesoft.cvbuilderai.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.utils.ProgressManager;
import com.mrteesoft.cvbuilderai.utils.CVDataManager;

public class WorkExperienceActivity extends AppCompatActivity {
    private EditText etJobTitle, etCompanyName, etStartDate, etEndDate, etResponsibilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etJobTitle = findViewById(R.id.etJobTitle);
        etCompanyName = findViewById(R.id.etCompanyName);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        etResponsibilities = findViewById(R.id.etResponsibilities);
    }

    private void setupClickListeners() {
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView btnSuggestJobTitle = findViewById(R.id.btnSuggestJobTitle);
        TextView btnSuggestResponsibilities = findViewById(R.id.btnSuggestResponsibilities);
        TextView btnSave = findViewById(R.id.btnSave);

        btnBack.setOnClickListener(v -> onBackPressed());
        btnSuggestJobTitle.setOnClickListener(v -> suggestJobTitle());
        btnSuggestResponsibilities.setOnClickListener(v -> suggestResponsibilities());
        btnSave.setOnClickListener(v -> saveWorkExperience());
    }

    private void suggestJobTitle() {
        String[] jobTitles = {"Software Developer", "Marketing Manager", "Data Analyst", "Project Manager", "Sales Representative"};
        String suggestion = jobTitles[(int) (Math.random() * jobTitles.length)];
        etJobTitle.setText(suggestion);
        Toast.makeText(this, "Job title suggested!", Toast.LENGTH_SHORT).show();
    }

    private void suggestResponsibilities() {
        String jobTitle = etJobTitle.getText().toString().trim().toLowerCase();
        String responsibilities;
        
        if (jobTitle.contains("developer") || jobTitle.contains("engineer")) {
            responsibilities = "• Developed and maintained software applications using modern technologies\n" +
                             "• Collaborated with cross-functional teams to deliver high-quality solutions\n" +
                             "• Participated in code reviews and implemented best practices\n" +
                             "• Troubleshot and resolved technical issues efficiently";
        } else if (jobTitle.contains("manager")) {
            responsibilities = "• Led and managed a team of professionals to achieve organizational goals\n" +
                             "• Developed and implemented strategic plans and processes\n" +
                             "• Coordinated projects and ensured timely delivery of results\n" +
                             "• Conducted performance evaluations and provided mentorship";
        } else {
            responsibilities = "• Executed key responsibilities with attention to detail and quality\n" +
                             "• Collaborated effectively with team members and stakeholders\n" +
                             "• Contributed to process improvements and efficiency gains\n" +
                             "• Maintained professional standards and met performance targets";
        }
        
        etResponsibilities.setText(responsibilities);
        Toast.makeText(this, "Responsibilities suggested based on job title!", Toast.LENGTH_SHORT).show();
    }

    private void saveWorkExperience() {
        String jobTitle = etJobTitle.getText().toString().trim();
        String company = etCompanyName.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();
        String responsibilities = etResponsibilities.getText().toString().trim();
        
        if (jobTitle.isEmpty()) {
            Toast.makeText(this, "Please enter job title", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (company.isEmpty()) {
            Toast.makeText(this, "Please enter company name", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Save all data
        CVDataManager.saveData(this, CVDataManager.KEY_JOB_TITLE, jobTitle);
        CVDataManager.saveData(this, CVDataManager.KEY_COMPANY_NAME, company);
        CVDataManager.saveData(this, CVDataManager.KEY_START_DATE, startDate);
        CVDataManager.saveData(this, CVDataManager.KEY_END_DATE, endDate);
        CVDataManager.saveData(this, CVDataManager.KEY_RESPONSIBILITIES, responsibilities);
        
        ProgressManager.markSectionCompleted(this, ProgressManager.SECTION_WORK_EXPERIENCE);
        Toast.makeText(this, "Work experience saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}