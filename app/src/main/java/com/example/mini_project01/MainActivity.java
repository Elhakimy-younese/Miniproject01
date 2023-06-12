package com.example.mini_project01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button loadusers, quit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadusers = findViewById(R.id.loaduser);
        quit = findViewById(R.id.quit);

        loadusers.setOnClickListener(this);
        quit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == loadusers){

            try {
                InputStream inputStream = getAssets().open("users.json");

                int code ;

                StringBuilder stringBuilder = new StringBuilder();
                String jsonString;
                code = inputStream.read();
                while (code != -1){
                    stringBuilder.append((char)code);

                    code = inputStream.read();
                }
                jsonString = stringBuilder.toString();
                Toast.makeText(this, jsonString, Toast.LENGTH_SHORT).show();


            } catch (IOException e) {
                e.printStackTrace();
            }




        } else if (v.getId() == R.id.quit) {
            finish();
        }
    }
}