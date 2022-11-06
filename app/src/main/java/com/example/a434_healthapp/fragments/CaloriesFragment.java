package com.example.a434_healthapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a434_healthapp.R;
import com.example.a434_healthapp.databinding.FragmentCaloriesBinding;
import com.example.a434_healthapp.databinding.FragmentProfileBinding;
import com.parse.ParseUser;

public class CaloriesFragment extends Fragment {


    FragmentCaloriesBinding binding;
    TextView tvWater;

    EditText breakfast, lunch, dinner, snack, water, steps;
    Button calculateTotal;
    TextView result;
    TextView waterComplete;
    TextView stepsComplete;


    public CaloriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCaloriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        breakfast = binding.tvbreakfastCal;
        lunch = binding.tvlunchCal;
        dinner = binding.tvdinnerCal;
        snack = binding.tvsnackCal;
        calculateTotal = binding.tvAddButton;
        water = binding.tvWaterIntake;
        waterComplete = binding.tvWaterComplete;
        steps = binding.tvStepsWalked;
        stepsComplete = binding.tvStepsComplete;
        result = binding.resultTotalCalories;

        calculateTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = breakfast.getText().toString();
                String temp1 = lunch.getText().toString();
                String temp2 = dinner.getText().toString();
                String temp3 = snack.getText().toString();
                String waterC = water.getText().toString();
                String stepsC = steps.getText().toString();

                int res = Integer.parseInt(temp) + Integer.parseInt(temp1) + Integer.parseInt(temp2)
                        + Integer.parseInt(temp3);

                ParseUser user = ParseUser.getCurrentUser();
                user.put("dailyCalories", res);
                user.put("dailySteps", Integer.parseInt(stepsC));
                user.put("dailyWater", Integer.parseInt(waterC));
                user.saveInBackground();

                int goalCalories = (int) user.get("goalCalories");
                int goalWater = (int) user.get("goalWater");
                int goalSteps = (int) user.get("goalSteps");

                result.setText("You ate " + res + " calories out of " + Integer.toString(goalCalories) + " goal calories.");
                waterComplete.setText("You drank " + waterC + " oz of water out of " +
                        Integer.toString(goalWater) + " oz goal.");
                stepsComplete.setText("You walked "+ stepsC +" out of " + Integer.toString(goalSteps) + " step goal.");

            }
        });

    }
}