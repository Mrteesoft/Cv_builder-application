package com.mrteesoft.cvbuilderai.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.models.WorkExperience;
import java.util.List;

public class WorkExperienceAdapter extends RecyclerView.Adapter<WorkExperienceAdapter.ViewHolder> {
    private List<WorkExperience> workExperienceList;

    public WorkExperienceAdapter(List<WorkExperience> workExperienceList) {
        this.workExperienceList = workExperienceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_experience, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkExperience workExp = workExperienceList.get(position);
        holder.txtJobTitle.setText(workExp.getJobTitle());
        holder.txtCompanyName.setText(workExp.getCompanyName());
        holder.txtDuration.setText(workExp.getStartDate() + " - " + (workExp.getEndDate() != null ? workExp.getEndDate() : "Present"));
        
        holder.btnDelete.setOnClickListener(v -> {
            workExperienceList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, workExperienceList.size());
        });
    }

    @Override
    public int getItemCount() {
        return workExperienceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJobTitle, txtCompanyName, txtDuration;
        Button btnEdit, btnDelete;

        ViewHolder(View itemView) {
            super(itemView);
            txtJobTitle = itemView.findViewById(R.id.txtJobTitle);
            txtCompanyName = itemView.findViewById(R.id.txtCompanyName);
            txtDuration = itemView.findViewById(R.id.txtDuration);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}