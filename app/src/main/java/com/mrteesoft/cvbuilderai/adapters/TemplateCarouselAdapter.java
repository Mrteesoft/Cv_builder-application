package com.mrteesoft.cvbuilderai.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.activities.CVFormActivity;
import com.mrteesoft.cvbuilderai.models.CVTemplate;
import com.mrteesoft.cvbuilderai.utils.Constants;
import java.util.List;

public class TemplateCarouselAdapter extends RecyclerView.Adapter<TemplateCarouselAdapter.ViewHolder> {
    private List<CVTemplate> templates;
    private Context context;

    public TemplateCarouselAdapter(List<CVTemplate> templates, Context context) {
        this.templates = templates;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template_carousel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CVTemplate template = templates.get(position);
        holder.txtTemplateName.setText(template.getTemplateName());
        
        holder.btnUseTemplate.setOnClickListener(v -> {
            Intent intent = new Intent(context, CVFormActivity.class);
            intent.putExtra(Constants.EXTRA_TEMPLATE_ID, template.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTemplatePreview;
        TextView txtTemplateName;
        Button btnUseTemplate;

        ViewHolder(View itemView) {
            super(itemView);
            imgTemplatePreview = itemView.findViewById(R.id.imgTemplatePreview);
            txtTemplateName = itemView.findViewById(R.id.txtTemplateName);
            btnUseTemplate = itemView.findViewById(R.id.btnUseTemplate);
        }
    }
}