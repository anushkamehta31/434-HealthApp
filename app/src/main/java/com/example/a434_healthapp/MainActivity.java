package com.example.a434_healthapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a434_healthapp.fragments.CaloriesFragment;
import com.example.a434_healthapp.fragments.FriendsFragment;
import com.example.a434_healthapp.fragments.HomeFragment;
import com.example.a434_healthapp.fragments.ProfileFragment;
import com.example.a434_healthapp.fragments.ToDoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        final Fragment homeFragment = new HomeFragment();
        final Fragment toDoFragment = new ToDoFragment();
        final Fragment caloriesFragment = new CaloriesFragment();
        final Fragment friendsFragment = new FriendsFragment();
        final Fragment profileFragment = new ProfileFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = homeFragment;
                        break;
                    case R.id.action_toDo:
                        fragment = toDoFragment;
                        break;
                    case R.id.action_calories:
                        fragment = caloriesFragment;
                        break;
                    case R.id.action_friends:
                        fragment = friendsFragment;
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = profileFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}