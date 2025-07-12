package com.mrteesoft.cvbuilderai.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.models.CVTemplate;
import com.mrteesoft.cvbuilderai.interfaces.OnTemplateSelectedListener;
import java.util.List;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.ViewHolder> {
    private List<CVTemplate> templates;
    private OnTemplateSelectedListener listener;

    public TemplateAdapter(List<CVTemplate> templates, OnTemplateSelectedListener listener) {
        this.templates = templates;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CVTemplate template = templates.get(position);
        holder.txtTemplateName.setText(template.getTemplateName());
        holder.btnSelect.setOnClickListener(v -> listener.onTemplateSelected(template));
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTemplate;
        TextView txtTemplateName;
        Button btnSelect;

        ViewHolder(View itemView) {
            super(itemView);
            imgTemplate = itemView.findViewById(R.id.imgTemplate);
            txtTemplateName = itemView.findViewById(R.id.txtTemplateName);
            btnSelect = itemView.findViewById(R.id.btnSelect);
        }
    }
}