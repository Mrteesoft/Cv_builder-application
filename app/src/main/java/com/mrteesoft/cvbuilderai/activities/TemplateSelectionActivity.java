package com.mrteesoft.cvbuilderai.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.adapters.TemplateAdapter;
import com.mrteesoft.cvbuilderai.database.dao.TemplateDao;
import com.mrteesoft.cvbuilderai.interfaces.OnTemplateSelectedListener;
import com.mrteesoft.cvbuilderai.models.CVTemplate;
import com.mrteesoft.cvbuilderai.utils.Constants;
import java.util.List;

public class TemplateSelectionActivity extends AppCompatActivity implements OnTemplateSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_selection);

        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Select Template");
        }
    }

    private void setupRecyclerView() {
        try {
            RecyclerView recyclerView = findViewById(R.id.recyclerTemplates);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

            TemplateDao templateDao = new TemplateDao(this);
            List<CVTemplate> templates = templateDao.getAllTemplates();
            
            if (templates.isEmpty()) {
                // Add fallback templates if database is empty
                CVTemplate template1 = new CVTemplate("Modern Professional", "modern");
                template1.setId(1);
                CVTemplate template2 = new CVTemplate("Classic Traditional", "classic");
                template2.setId(2);
                templates.add(template1);
                templates.add(template2);
            }
            
            TemplateAdapter adapter = new TemplateAdapter(templates, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    @Override
    public void onTemplateSelected(CVTemplate template) {
        Intent intent = new Intent(this, CVFormActivity.class);
        intent.putExtra(Constants.EXTRA_TEMPLATE_ID, template.getId());
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}