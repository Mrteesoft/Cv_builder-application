package com.mrteesoft.cvbuilderai.utils;

import android.content.Context;
import android.os.Environment;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.mrteesoft.cvbuilderai.models.PersonalInfo;
import java.io.File;
import java.io.FileNotFoundException;

public class PDFGenerator {
    
    public static String generateCV(Context context, PersonalInfo personalInfo) {
        try {
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String fileName = "CV_" + personalInfo.getFullName().replaceAll(" ", "_") + ".pdf";
            File pdfFile = new File(downloadsDir, fileName);
            
            PdfWriter writer = new PdfWriter(pdfFile.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            // Add content
            document.add(new Paragraph(personalInfo.getFullName()).setFontSize(20).setBold());
            document.add(new Paragraph(personalInfo.getEmail()));
            document.add(new Paragraph(personalInfo.getPhone()));
            
            if (personalInfo.getProfessionalSummary() != null && !personalInfo.getProfessionalSummary().isEmpty()) {
                document.add(new Paragraph("Professional Summary").setFontSize(16).setBold());
                document.add(new Paragraph(personalInfo.getProfessionalSummary()));
            }
            
            document.close();
            return pdfFile.getAbsolutePath();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}