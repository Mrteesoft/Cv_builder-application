package com.mrteesoft.cvbuilderai.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mrteesoft.cvbuilderai.database.DatabaseHelper;
import com.mrteesoft.cvbuilderai.models.CVTemplate;
import java.util.ArrayList;
import java.util.List;

public class TemplateDao {
    private DatabaseHelper dbHelper;

    public TemplateDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public List<CVTemplate> getAllTemplates() {
        List<CVTemplate> templates = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query("cv_templates", null, null, null, null, null, "id ASC");
            
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    CVTemplate template = new CVTemplate();
                    template.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    template.setTemplateName(cursor.getString(cursor.getColumnIndexOrThrow("template_name")));
                    template.setTemplateType(cursor.getString(cursor.getColumnIndexOrThrow("template_type")));
                    template.setPremium(cursor.getInt(cursor.getColumnIndexOrThrow("is_premium")) == 1);
                    templates.add(template);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
        
        return templates;
    }
}