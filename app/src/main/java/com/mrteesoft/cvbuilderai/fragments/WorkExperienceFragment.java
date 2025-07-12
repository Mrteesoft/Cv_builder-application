package com.mrteesoft.cvbuilderai.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.adapters.WorkExperienceAdapter;
import com.mrteesoft.cvbuilderai.models.WorkExperience;
import java.util.ArrayList;
import java.util.List;

public class WorkExperienceFragment extends Fragment {
    private RecyclerView recyclerView;
    private WorkExperienceAdapter adapter;
    private List<WorkExperience> workExperienceList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work_experience, container, false);
        
        recyclerView = view.findViewById(R.id.recyclerWorkExperience);
        Button btnAdd = view.findViewById(R.id.btnAddWorkExperience);
        
        setupRecyclerView();
        
        btnAdd.setOnClickListener(v -> addWorkExperience());
        
        return view;
    }

    private void setupRecyclerView() {
        workExperienceList = new ArrayList<>();
        adapter = new WorkExperienceAdapter(workExperienceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void addWorkExperience() {
        WorkExperience workExp = new WorkExperience();
        workExp.setJobTitle("Sample Job Title");
        workExp.setCompanyName("Sample Company");
        workExp.setStartDate("2023");
        workExp.setEndDate("Present");
        workExperienceList.add(workExp);
        adapter.notifyItemInserted(workExperienceList.size() - 1);
    }

    public List<WorkExperience> getWorkExperienceList() {
        return workExperienceList;
    }
}