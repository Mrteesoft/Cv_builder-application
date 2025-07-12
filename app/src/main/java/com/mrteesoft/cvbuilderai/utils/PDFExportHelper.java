package com.mrteesoft.cvbuilderai.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PDFExportHelper {
    
    private ActivityResultLauncher<String> createDocumentLauncher;
    private PDFExportCallback callback;
    
    public interface PDFExportCallback {
        void onLocationSelected(Uri uri);
        void onExportCancelled();
    }
    
    public PDFExportHelper(AppCompatActivity activity, PDFExportCallback callback) {
        this.callback = callback;
        
        createDocumentLauncher = activity.registerForActivityResult(
            new ActivityResultContracts.CreateDocument("application/pdf"),
            uri -> {
                if (uri != null) {
                    callback.onLocationSelected(uri);
                } else {
                    callback.onExportCancelled();
                }
            }
        );
    }
    
    public void exportPDF(String fileName) {
        createDocumentLauncher.launch(fileName + ".pdf");
    }
}