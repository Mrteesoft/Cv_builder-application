package com.mrteesoft.cvbuilderai.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.models.PersonalInfo;
import com.mrteesoft.cvbuilderai.utils.Constants;
import com.mrteesoft.cvbuilderai.utils.PDFGenerator;
import com.mrteesoft.cvbuilderai.utils.CVDataManager;
import android.graphics.pdf.PdfDocument;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;

public class CVPreviewActivity extends AppCompatActivity {
    private int templateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_preview);

        templateId = getIntent().getIntExtra(Constants.EXTRA_TEMPLATE_ID, 0);
        
        setupToolbar();
        setupPreview();
        setupButtons();
    }

    private void setupToolbar() {
        // Using custom header instead of toolbar
    }

    private void setupPreview() {
        // Personal Info
        TextView txtName = findViewById(R.id.txtPreviewName);
        TextView txtEmail = findViewById(R.id.txtPreviewEmail);
        TextView txtPhone = findViewById(R.id.txtPreviewPhone);
        TextView txtAddress = findViewById(R.id.txtPreviewAddress);
        TextView txtSummary = findViewById(R.id.txtPreviewSummary);
        
        // Work Experience
        TextView txtJobTitle = findViewById(R.id.txtJobTitle);
        TextView txtCompanyDate = findViewById(R.id.txtCompanyDate);
        TextView txtResponsibilities = findViewById(R.id.txtResponsibilities);
        
        // Education
        TextView txtDegree = findViewById(R.id.txtDegree);
        TextView txtSchool = findViewById(R.id.txtSchool);
        TextView txtGradDate = findViewById(R.id.txtGradDate);
        
        // Load saved data
        String name = CVDataManager.getData(this, CVDataManager.KEY_FULL_NAME);
        String email = CVDataManager.getData(this, CVDataManager.KEY_EMAIL);
        String phone = CVDataManager.getData(this, CVDataManager.KEY_PHONE);
        String address = CVDataManager.getData(this, CVDataManager.KEY_ADDRESS);
        String linkedin = CVDataManager.getData(this, CVDataManager.KEY_LINKEDIN);
        
        String jobTitle = CVDataManager.getData(this, CVDataManager.KEY_JOB_TITLE);
        String company = CVDataManager.getData(this, CVDataManager.KEY_COMPANY_NAME);
        String startDate = CVDataManager.getData(this, CVDataManager.KEY_START_DATE);
        String endDate = CVDataManager.getData(this, CVDataManager.KEY_END_DATE);
        String responsibilities = CVDataManager.getData(this, CVDataManager.KEY_RESPONSIBILITIES);
        
        String degree = CVDataManager.getData(this, CVDataManager.KEY_DEGREE);
        String school = CVDataManager.getData(this, CVDataManager.KEY_SCHOOL_NAME);
        String gradDate = CVDataManager.getData(this, CVDataManager.KEY_GRADUATION_DATE);
        
        // Set personal info
        txtName.setText(name.isEmpty() ? "Your Name" : name);
        txtEmail.setText(email.isEmpty() ? "your.email@example.com" : email);
        txtPhone.setText(phone.isEmpty() ? "+1 (555) 123-4567" : phone);
        txtAddress.setText(address.isEmpty() ? "City, State" : address);
        txtSummary.setText("Professional summary showcasing your skills and experience in your field.");
        
        // Set work experience
        txtJobTitle.setText(jobTitle.isEmpty() ? "Job Title" : jobTitle);
        String companyDateText = (company.isEmpty() ? "Company Name" : company) + 
                               " • " + 
                               (startDate.isEmpty() && endDate.isEmpty() ? "2020 - Present" : 
                                startDate + " - " + (endDate.isEmpty() ? "Present" : endDate));
        txtCompanyDate.setText(companyDateText);
        
        if (!responsibilities.isEmpty()) {
            String[] respArray = responsibilities.split("\\n");
            StringBuilder formattedResp = new StringBuilder();
            for (String resp : respArray) {
                if (!resp.trim().isEmpty()) {
                    formattedResp.append("• ").append(resp.trim()).append("\n");
                }
            }
            txtResponsibilities.setText(formattedResp.toString().trim());
        } else {
            txtResponsibilities.setText("• Key responsibility or achievement\n• Another important accomplishment\n• Third major contribution");
        }
        
        // Set education
        txtDegree.setText(degree.isEmpty() ? "Degree Name" : degree);
        txtSchool.setText(school.isEmpty() ? "University Name" : school);
        txtGradDate.setText(gradDate.isEmpty() ? "Graduation Year" : gradDate);
    }

    private void setupButtons() {
        findViewById(R.id.btnBack).setOnClickListener(v -> onBackPressed());
        
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnSavePDF = findViewById(R.id.btnSavePDF);
        Button btnShare = findViewById(R.id.btnShare);
        
        btnEdit.setOnClickListener(v -> onBackPressed());
        
        btnSavePDF.setOnClickListener(v -> {
            downloadPDF();
        });
        
        btnShare.setOnClickListener(v -> {
            shareCV();
        });
    }
    
    private void downloadPDF() {
        try {
            generateAndSavePDF();
            Toast.makeText(this, "CV downloaded as PDF successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error generating PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void shareCV() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Share CV");
        builder.setMessage("Choose format to share:");
        
        builder.setPositiveButton("PDF", (dialog, which) -> {
            shareCVAsPDF();
        });
        
        builder.setNegativeButton("JPEG", (dialog, which) -> {
            shareCVAsJPEG();
        });
        
        builder.setNeutralButton("Cancel", null);
        builder.show();
    }
    
    private void shareCVAsPDF() {
        try {
            java.io.File pdfFile = generateAndSavePDF();
            android.net.Uri pdfUri = androidx.core.content.FileProvider.getUriForFile(this, 
                getPackageName() + ".fileprovider", pdfFile);
            
            android.content.Intent shareIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("application/pdf");
            shareIntent.putExtra(android.content.Intent.EXTRA_STREAM, pdfUri);
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Professional CV");
            shareIntent.addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(android.content.Intent.createChooser(shareIntent, "Share CV as PDF"));
        } catch (Exception e) {
            Toast.makeText(this, "Error sharing PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void shareCVAsJPEG() {
        try {
            java.io.File jpegFile = generateAndSaveJPEG();
            android.net.Uri jpegUri = androidx.core.content.FileProvider.getUriForFile(this, 
                getPackageName() + ".fileprovider", jpegFile);
            
            android.content.Intent shareIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("image/jpeg");
            shareIntent.putExtra(android.content.Intent.EXTRA_STREAM, jpegUri);
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Professional CV");
            shareIntent.addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(android.content.Intent.createChooser(shareIntent, "Share CV as JPEG"));
        } catch (Exception e) {
            Toast.makeText(this, "Error sharing JPEG: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private String generateCVText() {
        StringBuilder cv = new StringBuilder();
        
        // Personal Info
        String name = CVDataManager.getData(this, CVDataManager.KEY_FULL_NAME);
        String email = CVDataManager.getData(this, CVDataManager.KEY_EMAIL);
        String phone = CVDataManager.getData(this, CVDataManager.KEY_PHONE);
        String address = CVDataManager.getData(this, CVDataManager.KEY_ADDRESS);
        
        cv.append("=== CURRICULUM VITAE ===\n\n");
        cv.append("Name: ").append(name.isEmpty() ? "Your Name" : name).append("\n");
        cv.append("Email: ").append(email.isEmpty() ? "your.email@example.com" : email).append("\n");
        cv.append("Phone: ").append(phone.isEmpty() ? "+1 (555) 123-4567" : phone).append("\n");
        cv.append("Address: ").append(address.isEmpty() ? "City, State" : address).append("\n\n");
        
        // Work Experience
        String jobTitle = CVDataManager.getData(this, CVDataManager.KEY_JOB_TITLE);
        String company = CVDataManager.getData(this, CVDataManager.KEY_COMPANY_NAME);
        String responsibilities = CVDataManager.getData(this, CVDataManager.KEY_RESPONSIBILITIES);
        
        if (!jobTitle.isEmpty() || !company.isEmpty()) {
            cv.append("=== WORK EXPERIENCE ===\n");
            cv.append("Position: ").append(jobTitle.isEmpty() ? "Job Title" : jobTitle).append("\n");
            cv.append("Company: ").append(company.isEmpty() ? "Company Name" : company).append("\n");
            if (!responsibilities.isEmpty()) {
                cv.append("Responsibilities:\n").append(responsibilities).append("\n");
            }
            cv.append("\n");
        }
        
        // Education
        String degree = CVDataManager.getData(this, CVDataManager.KEY_DEGREE);
        String school = CVDataManager.getData(this, CVDataManager.KEY_SCHOOL_NAME);
        
        if (!degree.isEmpty() || !school.isEmpty()) {
            cv.append("=== EDUCATION ===\n");
            cv.append("Degree: ").append(degree.isEmpty() ? "Degree Name" : degree).append("\n");
            cv.append("Institution: ").append(school.isEmpty() ? "University Name" : school).append("\n\n");
        }
        
        cv.append("Generated by Faith CV Builder");
        return cv.toString();
    }
    
    private void drawTwoColumnCV(android.graphics.Canvas canvas) {
        // Paint objects for different text styles
        android.graphics.Paint namePaint = new android.graphics.Paint();
        namePaint.setTextSize(32);
        namePaint.setColor(android.graphics.Color.BLACK);
        namePaint.setTypeface(android.graphics.Typeface.SANS_SERIF);
        namePaint.setFakeBoldText(true);
        
        android.graphics.Paint sectionPaint = new android.graphics.Paint();
        sectionPaint.setTextSize(16);
        sectionPaint.setColor(android.graphics.Color.BLACK);
        sectionPaint.setTypeface(android.graphics.Typeface.SANS_SERIF);
        sectionPaint.setFakeBoldText(true);
        
        android.graphics.Paint bodyPaint = new android.graphics.Paint();
        bodyPaint.setTextSize(14);
        bodyPaint.setColor(android.graphics.Color.BLACK);
        bodyPaint.setTypeface(android.graphics.Typeface.SANS_SERIF);
        
        android.graphics.Paint metaPaint = new android.graphics.Paint();
        metaPaint.setTextSize(13);
        metaPaint.setColor(android.graphics.Color.BLACK);
        metaPaint.setTypeface(android.graphics.Typeface.SANS_SERIF);
        
        android.graphics.Paint linePaint = new android.graphics.Paint();
        linePaint.setColor(android.graphics.Color.BLACK);
        linePaint.setStrokeWidth(1);
        
        // Get user data
        String name = CVDataManager.getData(this, CVDataManager.KEY_FULL_NAME);
        String email = CVDataManager.getData(this, CVDataManager.KEY_EMAIL);
        String phone = CVDataManager.getData(this, CVDataManager.KEY_PHONE);
        String address = CVDataManager.getData(this, CVDataManager.KEY_ADDRESS);
        String jobTitle = CVDataManager.getData(this, CVDataManager.KEY_JOB_TITLE);
        String company = CVDataManager.getData(this, CVDataManager.KEY_COMPANY_NAME);
        String degree = CVDataManager.getData(this, CVDataManager.KEY_DEGREE);
        String school = CVDataManager.getData(this, CVDataManager.KEY_SCHOOL_NAME);
        
        // Left column (main content) - 60% width
        int leftColX = 40;
        int leftColWidth = 320;
        int y = 60;
        
        // Header section
        canvas.drawText(name.isEmpty() ? "Your Name" : name, leftColX, y, namePaint);
        y += 50;
        
        canvas.drawText(email.isEmpty() ? "your.email@example.com" : email, leftColX, y, metaPaint);
        y += 25;
        canvas.drawText(phone.isEmpty() ? "+1 (555) 123-4567" : phone, leftColX, y, metaPaint);
        y += 25;
        canvas.drawText(address.isEmpty() ? "City, State" : address, leftColX, y, metaPaint);
        y += 40;
        
        // Professional Summary
        canvas.drawText("PROFESSIONAL SUMMARY", leftColX, y, sectionPaint);
        y += 20;
        canvas.drawLine(leftColX, y, leftColX + 50, y, linePaint);
        y += 25;
        canvas.drawText("Professional summary showcasing your skills", leftColX, y, bodyPaint);
        y += 20;
        canvas.drawText("and experience in your field.", leftColX, y, bodyPaint);
        y += 40;
        
        // Work Experience
        canvas.drawText("WORK EXPERIENCE", leftColX, y, sectionPaint);
        y += 20;
        canvas.drawLine(leftColX, y, leftColX + 50, y, linePaint);
        y += 25;
        
        canvas.drawText(jobTitle.isEmpty() ? "Job Title" : jobTitle, leftColX, y, bodyPaint);
        y += 25;
        canvas.drawText((company.isEmpty() ? "Company Name" : company) + " • 2020 - Present", leftColX, y, metaPaint);
        y += 30;
        canvas.drawText("• Key responsibility or achievement", leftColX + 12, y, bodyPaint);
        y += 20;
        canvas.drawText("• Another important accomplishment", leftColX + 12, y, bodyPaint);
        y += 20;
        canvas.drawText("• Third major contribution", leftColX + 12, y, bodyPaint);
        
        // Right column (sidebar) - 40% width with background
        int rightColX = 380;
        int rightColWidth = 180;
        
        // Draw sidebar background (light gray)
        android.graphics.Paint bgPaint = new android.graphics.Paint();
        bgPaint.setColor(0xFFF5F5F5);
        canvas.drawRect(rightColX - 20, 0, 595, 842, bgPaint);
        
        y = 60;
        
        // Skills section
        canvas.drawText("SKILLS", rightColX, y, sectionPaint);
        y += 20;
        canvas.drawLine(rightColX, y, rightColX + 30, y, linePaint);
        y += 25;
        canvas.drawText("• Technical Skills", rightColX, y, bodyPaint);
        y += 20;
        canvas.drawText("• Communication", rightColX, y, bodyPaint);
        y += 20;
        canvas.drawText("• Problem Solving", rightColX, y, bodyPaint);
        y += 20;
        canvas.drawText("• Leadership", rightColX, y, bodyPaint);
        y += 40;
        
        // Education section
        canvas.drawText("EDUCATION", rightColX, y, sectionPaint);
        y += 20;
        canvas.drawLine(rightColX, y, rightColX + 30, y, linePaint);
        y += 25;
        canvas.drawText(degree.isEmpty() ? "Degree Name" : degree, rightColX, y, bodyPaint);
        y += 20;
        canvas.drawText(school.isEmpty() ? "University Name" : school, rightColX, y, metaPaint);
        y += 20;
        canvas.drawText("Graduation Year", rightColX, y, metaPaint);
        y += 40;
        
        // Languages section
        canvas.drawText("LANGUAGES", rightColX, y, sectionPaint);
        y += 20;
        canvas.drawLine(rightColX, y, rightColX + 30, y, linePaint);
        y += 25;
        canvas.drawText("• English (Native)", rightColX, y, bodyPaint);
        y += 20;
        canvas.drawText("• Spanish (Fluent)", rightColX, y, bodyPaint);
        y += 20;
        canvas.drawText("• French (Basic)", rightColX, y, bodyPaint);
    }
    
    private java.io.File generateAndSavePDF() throws Exception {
        android.graphics.pdf.PdfDocument document = new android.graphics.pdf.PdfDocument();
        android.graphics.pdf.PdfDocument.PageInfo pageInfo = new android.graphics.pdf.PdfDocument.PageInfo.Builder(595, 842, 1).create();
        android.graphics.pdf.PdfDocument.Page page = document.startPage(pageInfo);
        android.graphics.Canvas canvas = page.getCanvas();
        
        drawTwoColumnCV(canvas);
        
        document.finishPage(page);
        
        java.io.File file = new java.io.File(getExternalFilesDir(null), "my_cv.pdf");
        java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
        document.writeTo(fos);
        document.close();
        fos.close();
        
        return file;
    }
    
    private java.io.File generateAndSaveJPEG() throws Exception {
        android.graphics.Bitmap bitmap = android.graphics.Bitmap.createBitmap(595, 842, android.graphics.Bitmap.Config.ARGB_8888);
        android.graphics.Canvas canvas = new android.graphics.Canvas(bitmap);
        canvas.drawColor(android.graphics.Color.WHITE);
        
        drawTwoColumnCV(canvas);
        
        java.io.File file = new java.io.File(getExternalFilesDir(null), "my_cv.jpg");
        java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
        bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 90, fos);
        fos.close();
        
        return file;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}