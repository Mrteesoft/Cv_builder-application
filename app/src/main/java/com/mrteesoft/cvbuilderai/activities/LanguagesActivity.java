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

public class LanguagesActivity extends AppCompatActivity {
    private EditText etNativeLanguage, etFluentLanguages, etBasicLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        etNativeLanguage = findViewById(R.id.etNativeLanguage);
        etFluentLanguages = findViewById(R.id.etFluentLanguages);
        etBasicLanguages = findViewById(R.id.etBasicLanguages);
    }

    private void setupClickListeners() {
        ImageView btnBack = findViewById(R.id.btnBack);
        LinearLayout btnSuggestLanguages = findViewById(R.id.btnSuggestLanguages);
        TextView btnSave = findViewById(R.id.btnSave);

        btnBack.setOnClickListener(v -> onBackPressed());
        btnSuggestLanguages.setOnClickListener(v -> suggestLanguages());
        btnSave.setOnClickListener(v -> saveLanguages());
    }

    private void suggestLanguages() {
        String[] languages = {"Spanish, French", "German, Italian", "Mandarin, Japanese", "Portuguese, Dutch"};
        String suggestion = languages[(int) (Math.random() * languages.length)];
        etFluentLanguages.setText(suggestion);
        Toast.makeText(this, "Languages suggested!", Toast.LENGTH_SHORT).show();
    }

    private void saveLanguages() {
        ProgressManager.markSectionCompleted(this, ProgressManager.SECTION_LANGUAGES);
        Toast.makeText(this, "Languages saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}