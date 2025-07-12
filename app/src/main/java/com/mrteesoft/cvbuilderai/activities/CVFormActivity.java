package com.mrteesoft.cvbuilderai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.fragments.PersonalInfoFragment;
import com.mrteesoft.cvbuilderai.fragments.WorkExperienceFragment;
import com.mrteesoft.cvbuilderai.utils.Constants;

public class CVFormActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private Button btnPrevious, btnNext;
    private int templateId;
    private PersonalInfoFragment personalInfoFragment;
    private WorkExperienceFragment workExperienceFragment;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_form);

        templateId = getIntent().getIntExtra(Constants.EXTRA_TEMPLATE_ID, 0);
        
        setupToolbar();
        setupViewPager();
        setupButtons();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Create CV");
        }
    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        
        personalInfoFragment = new PersonalInfoFragment();
        workExperienceFragment = new WorkExperienceFragment();
        
        FragmentAdapter adapter = new FragmentAdapter(this);
        viewPager.setAdapter(adapter);
        
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Personal"); break;
                case 1: tab.setText("Experience"); break;
                default: tab.setText("Tab " + (position + 1)); break;
            }
        }).attach();
        
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                updateButtons();
            }
        });
    }

    private void setupButtons() {
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        
        btnPrevious.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() > 0) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
        
        btnNext.setOnClickListener(v -> {
            if (currentPage < 1) {
                if (currentPage == 0 && !personalInfoFragment.isValid()) {
                    Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewPager.setCurrentItem(currentPage + 1);
            } else {
                // Last page - go to preview
                Intent intent = new Intent(this, CVPreviewActivity.class);
                intent.putExtra(Constants.EXTRA_TEMPLATE_ID, templateId);
                startActivity(intent);
            }
        });
        
        updateButtons();
    }
    
    private void updateButtons() {
        btnPrevious.setEnabled(currentPage > 0);
        btnNext.setText(currentPage == 1 ? "Preview" : "Next");
    }

    private class FragmentAdapter extends FragmentStateAdapter {
        public FragmentAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return personalInfoFragment;
                case 1: return workExperienceFragment;
                default: return personalInfoFragment;
            }
        }

        @Override
        public int getItemCount() {
            return 2; // Personal info and work experience
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}