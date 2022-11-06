package com.example.a434_healthapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.a434_healthapp.R;
import com.example.a434_healthapp.adapters.FriendsAdapter;
import com.example.a434_healthapp.databinding.FragmentFriendsBinding;
import com.example.a434_healthapp.databinding.FragmentProfileBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendsFragment extends Fragment {

    FragmentFriendsBinding binding;

    private Map<String, ParseUser> users;
    private List<String> usernames;
    ArrayAdapter arrayAdapter;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFriendsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        users = new HashMap<String, ParseUser>();
        usernames = new ArrayList<>();

        List<ParseUser> friendsList = getUserFriends();
        initializeFindUsers(view);

        RecyclerView rvFriends = binding.rvFriendsInformation;
        FriendsAdapter friendsAdapter = new FriendsAdapter(friendsList);
        rvFriends.setAdapter(friendsAdapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayAdapter = new ArrayAdapter(getContext(), R.layout.dropdown_item, usernames);
        binding.autoCompleteTextView.setAdapter(arrayAdapter);

        binding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // add friends to list
                ParseUser user = ParseUser.getCurrentUser();
                ParseRelation<ParseObject> relation = user.getRelation("friends");
                relation.add((ParseUser) users.get(usernames.get(i)));
                friendsList.add((ParseUser) users.get(usernames.get(i)));
                friendsAdapter.notifyDataSetChanged();
                try {
                    user.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public List<ParseUser> getUserFriends() {
        ParseUser user = ParseUser.getCurrentUser();
        ParseRelation<ParseUser> relation = user.getRelation("friends");
        try {
            return relation.getQuery().find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Creates a query to find all users and allows current user to search for users to add to the group */
    private void initializeFindUsers(View view) {
        ParseQuery<ParseUser> query = ParseQuery.getQuery(ParseUser.class);
        query.include("objectID");
        query.include("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> allUsers, ParseException e) {
                if (e != null) {
                    Log.e("FriendsFragment", "Issue with getting users", e);
                    return;
                }
                for (ParseUser parseUser : allUsers) {
                    ParseUser user = (ParseUser) parseUser;
                    String username = user.getUsername();
                    users.put(username, user);
                    usernames.add(username);
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        });
    }
}