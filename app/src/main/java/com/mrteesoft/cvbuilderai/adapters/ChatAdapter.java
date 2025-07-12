package com.mrteesoft.cvbuilderai.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.models.ChatMessage;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        
        if (message.isUser()) {
            holder.userMessageLayout.setVisibility(View.VISIBLE);
            holder.aiMessageLayout.setVisibility(View.GONE);
            holder.txtUserMessage.setText(message.getMessage());
            holder.txtUserTime.setText(getCurrentTime());
        } else {
            holder.userMessageLayout.setVisibility(View.GONE);
            holder.aiMessageLayout.setVisibility(View.VISIBLE);
            holder.txtAiMessage.setText(message.getMessage());
            holder.txtAiTime.setText(getCurrentTime());
        }
    }
    
    private String getCurrentTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("h:mm a", java.util.Locale.getDefault());
        return sdf.format(new java.util.Date());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout userMessageLayout, aiMessageLayout;
        TextView txtUserMessage, txtAiMessage, txtUserTime, txtAiTime;

        ViewHolder(View itemView) {
            super(itemView);
            userMessageLayout = itemView.findViewById(R.id.userMessageLayout);
            aiMessageLayout = itemView.findViewById(R.id.aiMessageLayout);
            txtUserMessage = itemView.findViewById(R.id.txtUserMessage);
            txtAiMessage = itemView.findViewById(R.id.txtAiMessage);
            txtUserTime = itemView.findViewById(R.id.txtUserTime);
            txtAiTime = itemView.findViewById(R.id.txtAiTime);
        }
    }
}