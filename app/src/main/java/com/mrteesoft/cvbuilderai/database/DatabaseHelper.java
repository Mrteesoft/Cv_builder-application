package com.mrteesoft.cvbuilderai.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mrteesoft.cvbuilderai.utils.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
        // Users table
        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id TEXT UNIQUE NOT NULL," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

        // CV Templates table
        db.execSQL("CREATE TABLE cv_templates (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "template_name TEXT NOT NULL," +
                "template_type TEXT NOT NULL," +
                "preview_image_path TEXT," +
                "layout_config TEXT," +
                "is_premium BOOLEAN DEFAULT 0," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

        // CV Documents table
        db.execSQL("CREATE TABLE cv_documents (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id TEXT NOT NULL," +
                "template_id INTEGER NOT NULL," +
                "cv_title TEXT NOT NULL," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (template_id) REFERENCES cv_templates(id)," +
                "FOREIGN KEY (user_id) REFERENCES users(user_id))");

        // Personal Information table
        db.execSQL("CREATE TABLE personal_info (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cv_id INTEGER NOT NULL," +
                "full_name TEXT NOT NULL," +
                "email TEXT," +
                "phone TEXT," +
                "address TEXT," +
                "city TEXT," +
                "country TEXT," +
                "postal_code TEXT," +
                "profile_image_path TEXT," +
                "professional_summary TEXT," +
                "linkedin_url TEXT," +
                "website_url TEXT," +
                "FOREIGN KEY (cv_id) REFERENCES cv_documents(id) ON DELETE CASCADE)");

        // Work Experience table
        db.execSQL("CREATE TABLE work_experience (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cv_id INTEGER NOT NULL," +
                "company_name TEXT NOT NULL," +
                "job_title TEXT NOT NULL," +
                "start_date TEXT NOT NULL," +
                "end_date TEXT," +
                "is_current BOOLEAN DEFAULT 0," +
                "location TEXT," +
                "description TEXT," +
                "display_order INTEGER DEFAULT 0," +
                "FOREIGN KEY (cv_id) REFERENCES cv_documents(id) ON DELETE CASCADE)");

        // Education table
        db.execSQL("CREATE TABLE education (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cv_id INTEGER NOT NULL," +
                "institution_name TEXT NOT NULL," +
                "degree TEXT NOT NULL," +
                "field_of_study TEXT," +
                "start_date TEXT," +
                "end_date TEXT," +
                "grade_gpa TEXT," +
                "location TEXT," +
                "description TEXT," +
                "display_order INTEGER DEFAULT 0," +
                "FOREIGN KEY (cv_id) REFERENCES cv_documents(id) ON DELETE CASCADE)");

        // Skills table
        db.execSQL("CREATE TABLE skills (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cv_id INTEGER NOT NULL," +
                "skill_name TEXT NOT NULL," +
                "skill_level TEXT," +
                "skill_category TEXT," +
                "display_order INTEGER DEFAULT 0," +
                "FOREIGN KEY (cv_id) REFERENCES cv_documents(id) ON DELETE CASCADE)");

        // Projects table
        db.execSQL("CREATE TABLE projects (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cv_id INTEGER NOT NULL," +
                "project_name TEXT NOT NULL," +
                "description TEXT," +
                "technologies_used TEXT," +
                "start_date TEXT," +
                "end_date TEXT," +
                "project_url TEXT," +
                "display_order INTEGER DEFAULT 0," +
                "FOREIGN KEY (cv_id) REFERENCES cv_documents(id) ON DELETE CASCADE)");

        // Certifications table
        db.execSQL("CREATE TABLE certifications (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cv_id INTEGER NOT NULL," +
                "certification_name TEXT NOT NULL," +
                "issuing_organization TEXT," +
                "issue_date TEXT," +
                "expiry_date TEXT," +
                "credential_id TEXT," +
                "credential_url TEXT," +
                "display_order INTEGER DEFAULT 0," +
                "FOREIGN KEY (cv_id) REFERENCES cv_documents(id) ON DELETE CASCADE)");

        insertDefaultTemplates(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS certifications");
        db.execSQL("DROP TABLE IF EXISTS projects");
        db.execSQL("DROP TABLE IF EXISTS skills");
        db.execSQL("DROP TABLE IF EXISTS education");
        db.execSQL("DROP TABLE IF EXISTS work_experience");
        db.execSQL("DROP TABLE IF EXISTS personal_info");
        db.execSQL("DROP TABLE IF EXISTS cv_documents");
        db.execSQL("DROP TABLE IF EXISTS cv_templates");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    private void insertDefaultTemplates(SQLiteDatabase db) {
        try {
            db.execSQL("INSERT INTO cv_templates (template_name, template_type, is_premium) VALUES " +
                    "('Modern Professional', 'modern', 0), " +
                    "('Classic Traditional', 'classic', 0)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}