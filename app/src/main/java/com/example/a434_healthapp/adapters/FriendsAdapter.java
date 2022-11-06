package com.example.a434_healthapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a434_healthapp.R;
import com.parse.ParseUser;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private List<ParseUser> mfriends;

    public FriendsAdapter(List<ParseUser> friends) {
        mfriends = friends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_friend, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseUser user = mfriends.get(position);
        holder.bind(user);

    }

    @Override
    public int getItemCount() {
        return mfriends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        TextView tvGoalCalories;
        TextView tvGoalWater;

        public ViewHolder(View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUserName);
            tvGoalCalories = itemView.findViewById(R.id.tvGoalCalories);
            tvGoalWater = itemView.findViewById(R.id.tvGoalWater);

        }

        public void bind(ParseUser user) {
            tvUsername.setText(user.getUsername());

            int goalCalories = (int) user.get("goalCalories");
            int goalWater = (int) user.get("goalWater");

            tvGoalWater.setText(Integer.toString(goalWater) + " oz per day");
            tvGoalCalories.setText(Integer.toString(goalCalories) + " calories per day");
        }
    }
}
