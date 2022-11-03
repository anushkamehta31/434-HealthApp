package com.example.a434_healthapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a434_healthapp.LoginActivity;
import com.example.a434_healthapp.MainActivity;
import com.example.a434_healthapp.R;
import com.example.a434_healthapp.databinding.FragmentProfileBinding;
import com.google.android.material.button.MaterialButton;
import com.parse.ParseUser;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    TextView tvUsername;
    TextView tvAge;
    TextView tvWeight;
    TextView tvHeight;
    TextView tvGoalWeight;
    TextView tvGoalWater;
    TextView tvGoalCalories;
    TextView tvGoalSteps;
    MaterialButton btnLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate view references
        tvUsername = binding.tvUserName;
        tvAge = binding.tvAge;
        tvHeight = binding.tvHeight;
        tvWeight = binding.tvWeight;
        tvGoalCalories = binding.tvGoalCalories;
        tvGoalWater = binding.tvGoalWater;
        tvGoalWeight = binding.tvGoalWeight;
        tvGoalSteps = binding.tvGoalSteps;
        btnLogout = binding.btnLogout;

        ParseUser currentUser = ParseUser.getCurrentUser();
        int weight = (int) currentUser.get("weight");
        int height = (int) currentUser.get("height");
        int age = (int) currentUser.get("age");
        int goalWeight = (int) currentUser.get("goalWeight");
        int goalCalories = (int) currentUser.get("goalCalories");
        int goalSteps = (int) currentUser.get("goalSteps");
        int goalWater = (int) currentUser.get("goalWater");

        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        tvWeight.setText(Integer.toString(weight) + " lbs");
        tvHeight.setText(Integer.toString(height) + " in");
        tvAge.setText(Integer.toString(age) + " yrs");
        tvGoalWeight.setText(Integer.toString(goalWeight) + " lbs");
        tvGoalWater.setText(Integer.toString(goalWater) + " oz per day");
        tvGoalCalories.setText(Integer.toString(goalCalories) + " calories per day");
        tvGoalSteps.setText(Integer.toString(goalSteps) + " steps per day");

        // Set click listener on logout button to return to log in screen
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                MainActivity activity = (MainActivity) getContext();
                activity.finish();
            }
        });

    }
}