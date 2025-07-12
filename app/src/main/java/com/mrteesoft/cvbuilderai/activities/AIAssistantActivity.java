package com.mrteesoft.cvbuilderai.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.mrteesoft.cvbuilderai.utils.AdManager;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.adapters.ChatAdapter;
import com.mrteesoft.cvbuilderai.models.ChatMessage;
import java.util.ArrayList;
import java.util.List;

public class AIAssistantActivity extends AppCompatActivity {
    private RecyclerView recyclerChat;
    private EditText etMessage;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_assistant);

        setupToolbar();
        setupViews();
        setupChat();
        loadAds();
    }

    private void setupToolbar() {
        // No toolbar needed - using custom header
    }

    private void setupViews() {
        recyclerChat = findViewById(R.id.recyclerChat);
        etMessage = findViewById(R.id.etMessage);
        LinearLayout btnSend = findViewById(R.id.btnSend);
        
        // Back button
        findViewById(R.id.btnBack).setOnClickListener(v -> onBackPressed());
        
        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void setupChat() {
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerChat.setAdapter(chatAdapter);

        // Welcome message
        addMessage("Hello! I'm your CV assistant. How can I help you create a professional CV today?", false);
    }

    private void sendMessage() {
        String message = etMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            addMessage(message, true);
            etMessage.setText("");
            
            // Simulate AI response
            simulateAIResponse(message);
        }
    }

    private void addMessage(String message, boolean isUser) {
        ChatMessage chatMessage = new ChatMessage(message, isUser);
        chatMessages.add(chatMessage);
        chatAdapter.notifyItemInserted(chatMessages.size() - 1);
        recyclerChat.scrollToPosition(chatMessages.size() - 1);
    }

    private void simulateAIResponse(String userMessage) {
        String response;
        if (userMessage.toLowerCase().contains("template")) {
            response = "I recommend starting with our Modern template for a professional look. It's clean and ATS-friendly!";
        } else if (userMessage.toLowerCase().contains("skill")) {
            response = "List your top 6-8 relevant skills. Include both technical and soft skills that match the job you're applying for.";
        } else if (userMessage.toLowerCase().contains("experience")) {
            response = "Use action verbs and quantify your achievements. For example: 'Increased sales by 25%' instead of 'Responsible for sales'.";
        } else {
            response = "That's a great question! For CV writing, focus on relevance, clarity, and achievements. What specific section would you like help with?";
        }
        
        // Simulate delay - no interstitial during conversation per Google policy
        recyclerChat.postDelayed(() -> addMessage(response, false), 1000);
    }

    private void loadAds() {
        // Load banner ad
        AdView adView = findViewById(R.id.adViewAI);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        // Load interstitial ad
        InterstitialAd.load(this, "ca-app-pub-6483271377992812/1699284347", adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(InterstitialAd interstitialAd) {
                    mInterstitialAd = interstitialAd;
                }
            });
    }

    @Override
    public boolean onSupportNavigateUp() {
        showInterstitialOnExit();
        return true;
    }
    
    @Override
    public void onBackPressed() {
        showInterstitialOnExit();
    }
    
    private void showInterstitialOnExit() {
        // Only show exit interstitial if user had meaningful interaction AND timing allows
        if (mInterstitialAd != null && chatMessages.size() >= 3 && 
            AdManager.shouldShowInterstitial(this, chatMessages.size())) {
            mInterstitialAd.show(this);
            AdManager.recordInterstitialShown(this);
            
            mInterstitialAd.setFullScreenContentCallback(new com.google.android.gms.ads.FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    finish();
                }
                
                @Override
                public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                    finish();
                }
            });
        } else {
            finish();
        }
    }
}