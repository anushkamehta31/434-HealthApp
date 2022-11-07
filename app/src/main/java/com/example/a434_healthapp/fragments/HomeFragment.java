package com.example.a434_healthapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a434_healthapp.LoginActivity;
import com.example.a434_healthapp.MainActivity;
import com.example.a434_healthapp.R;

import com.example.a434_healthapp.databinding.FragmentHomeBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.parse.ParseUser;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    TextView username;
    TextView water;
    TextView calories;
    TextView steps;
    ProgressBar progressBarWater;
    ProgressBar progressBarSteps;
    ProgressBar progressBarCalories;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View v = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate view references
        username = binding.username;
        water = binding.water;
        calories = binding.calories;
        steps = binding.steps;
        progressBarWater = binding.progressBarWater;
        progressBarSteps = binding.progressBar2;
        progressBarCalories = binding.progressBar3;
        TextView tvYoutubeLink = binding.tvYoutubeLink;

        ParseUser currentUser = ParseUser.getCurrentUser();

        int goalWater = (int) currentUser.get("goalWater");
        int goalCalories = (int) currentUser.get("goalCalories");
        int goalSteps = (int) currentUser.get("goalSteps");

        int dailyCalories = (int) currentUser.get("dailyCalories");
        int dailyWater = (int) currentUser.get("dailyWater");
        int dailySteps = (int) currentUser.get("dailySteps");

        int remainWater = goalWater - dailyWater;
        int remainSteps = goalSteps - dailySteps;
        int remainCalories = goalCalories - dailyCalories;


        double progressWater = ((double) dailyWater / (double) goalWater) * 100;
        double progressSteps = ((double) dailySteps / (double) goalSteps) * 100;
        double progressCalories = ((double) dailyCalories / (double) goalCalories) * 100;

        progressBarWater.setProgress((int) progressWater);
        progressBarSteps.setProgress((int) progressSteps);
        progressBarCalories.setProgress((int) progressCalories);

        username.setText("Hello, " + ParseUser.getCurrentUser().getUsername());
//        water.setText(Integer.toString(remainWater) + " oz left");
//        calories.setText(Integer.toString(remainCalories) + " calories left");
//        steps.setText(Integer.toString(remainSteps) + " steps left");

        String level = (String) currentUser.get("level");

        if (level.equals("Beginner")) {
            tvYoutubeLink.setText("https://www.youtube.com/watch?v=ZeJLIdQenTo&ab_channel=MadFit");
        } else if (level.equals("Intermediate")) {
            tvYoutubeLink.setText("https://www.youtube.com/watch?v=wLYeRlyyncY");
        } else {
            tvYoutubeLink.setText("https://www.youtube.com/watch?v=yplP5cLuyf4");
        }

    }


}