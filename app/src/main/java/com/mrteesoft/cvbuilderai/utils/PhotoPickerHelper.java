package com.mrteesoft.cvbuilderai.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoPickerHelper {
    
    private ActivityResultLauncher<String> photoPickerLauncher;
    private PhotoPickerCallback callback;
    
    public interface PhotoPickerCallback {
        void onPhotoSelected(Uri photoUri);
        void onPhotoSelectionCancelled();
    }
    
    public PhotoPickerHelper(AppCompatActivity activity, PhotoPickerCallback callback) {
        this.callback = callback;
        
        photoPickerLauncher = activity.registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    callback.onPhotoSelected(uri);
                } else {
                    callback.onPhotoSelectionCancelled();
                }
            }
        );
    }
    
    public void selectPhoto() {
        photoPickerLauncher.launch("image/*");
    }
}