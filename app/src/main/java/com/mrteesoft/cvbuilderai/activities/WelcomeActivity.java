package com.mrteesoft.cvbuilderai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.mrteesoft.cvbuilderai.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Make status bar transparent
        getWindow().setFlags(
            android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        
        setContentView(R.layout.activity_welcome);

        Button btnGetStarted = findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}