package com.example.mini_project01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class User_Details extends AppCompatActivity {
    TextView Firstname,Lastname,  City;
    ImageView Gender;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Firstname = findViewById(R.id.tvfirstname2);
        Lastname = findViewById(R.id.tvlastname);
        Gender = findViewById(R.id.genderimage);
        City = findViewById(R.id.tvCity);

        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        String gender = getIntent().getStringExtra("gender");
        String city = getIntent().getStringExtra("city");

        Firstname.setText(firstname);
        Lastname.setText(lastname);
        City.setText(city);
        if (gender.equals("male")){
            Gender.setImageResource(R.drawable.baseline_male_24);
        }
        else
            Gender.setImageResource(R.drawable.baseline_female_24);



    }
}