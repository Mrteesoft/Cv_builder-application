package com.mrteesoft.cvbuilderai.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import com.mrteesoft.cvbuilderai.R;
import com.mrteesoft.cvbuilderai.models.PersonalInfo;

public class PersonalInfoFragment extends Fragment {
    private TextInputEditText etFullName, etEmail, etPhone, etAddress, etCity, etCountry, etSummary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        
        etFullName = view.findViewById(R.id.etFullName);
        etEmail = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        etAddress = view.findViewById(R.id.etAddress);
        etCity = view.findViewById(R.id.etCity);
        etCountry = view.findViewById(R.id.etCountry);
        etSummary = view.findViewById(R.id.etSummary);
        
        return view;
    }

    public PersonalInfo getPersonalInfo() {
        PersonalInfo info = new PersonalInfo();
        info.setFullName(getText(etFullName));
        info.setEmail(getText(etEmail));
        info.setPhone(getText(etPhone));
        info.setAddress(getText(etAddress));
        info.setCity(getText(etCity));
        info.setCountry(getText(etCountry));
        info.setProfessionalSummary(getText(etSummary));
        return info;
    }

    private String getText(TextInputEditText editText) {
        return editText.getText() != null ? editText.getText().toString().trim() : "";
    }

    public boolean isValid() {
        return !getText(etFullName).isEmpty();
    }
}