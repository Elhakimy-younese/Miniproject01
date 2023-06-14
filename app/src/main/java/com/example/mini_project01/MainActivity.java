package com.example.mini_project01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button loadusers ;
    TextView quit;
    ListView LvUsers;
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadusers = findViewById(R.id.loaduser);
        quit = findViewById(R.id.tvquit);
        LvUsers = findViewById(R.id.lvusers);

        loadusers.setOnClickListener(this);
        quit.setOnClickListener(this);

        quit.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void swipeLeft() {
                finish();

            }

            @Override
            public void swipeRight() {
                Toast.makeText(MainActivity.this, "if you want to leave the app swipe lift", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == loadusers){
            Usersadapter adapter = new Usersadapter(this, getusers());

            LvUsers.setAdapter(adapter);


        }
    }

    private ArrayList<User> getusers() {
        ArrayList<User> usersFullname = new ArrayList<>();

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
            
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray =jsonObject.getJSONArray("users") ;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject user = jsonArray.getJSONObject(i);
                JSONObject username = user.getJSONObject("name");
                usersFullname.add(new User(
                        username.getString("first"),
                        username.getString("last"),
                        user.getString("gender"),
                        user.getString("city")
                        ) );


            }
            


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return usersFullname;
    }
}