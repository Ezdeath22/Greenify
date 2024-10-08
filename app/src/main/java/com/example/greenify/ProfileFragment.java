package com.example.greenify;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide; // Ensure you have the Glide library included

public class ProfileFragment extends Fragment {

    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewBirthday;
    private TextView textViewGender;
    private Button saveButton;
    private ImageView profileImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize your TextViews
        textViewFirstName = view.findViewById(R.id.textViewFirstName);
        textViewLastName = view.findViewById(R.id.textViewLastName);
        textViewBirthday = view.findViewById(R.id.textViewBirthday);
        textViewGender = view.findViewById(R.id.textViewGender);
        profileImageView = view.findViewById(R.id.profile_image); // Initialize the ImageView

        // Initialize the save button
        saveButton = view.findViewById(R.id.save_button);

        // Set click listener for save button
        saveButton.setOnClickListener(v -> {
            // Code to handle saving profile information if needed
        });

        // Fetch user data from SharedPreferences
        fetchUserData();

        return view;
    }

    private void fetchUserData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_profile", Context.MODE_PRIVATE);

        // Retrieve user data or set defaults if not found
        String firstName = sharedPreferences.getString("first_name", "Hanzy"); // Default first name
        String lastName = sharedPreferences.getString("last_name", "Bai"); // Default last name
        String birthday = sharedPreferences.getString("birthday", "Feb 8, 2004"); // Default birthday
        String gender = sharedPreferences.getString("gender", "Male"); // Default gender
        String profileImageUri = sharedPreferences.getString("profile_image", ""); // Example URI for the profile image

        // Set data to views
        textViewFirstName.setText(firstName);
        textViewLastName.setText(lastName);
        textViewBirthday.setText(birthday);
        textViewGender.setText(gender);

        // Load image into ImageView as a circular image
        if (!profileImageUri.isEmpty()) {
            Glide.with(this)
                    .load(profileImageUri)
                    .circleCrop() // This method makes the image circular
                    .into(profileImageView); // Load the profile image
        }
    }
}
