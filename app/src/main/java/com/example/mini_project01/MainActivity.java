package com.example.mini_project01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                
                JSONObject jsonObject = new JSONObject(jsonString);
//                JSONArray jsonArray = (JSONArray) jsonObject.get("users") ;
                JSONArray jsonArray =jsonObject.getJSONArray("users") ;
//                Toast.makeText(this,Integer.toString(jsonArray.length())  , Toast.LENGTH_SHORT).show();
                StringBuilder stringBuilderFullname = new StringBuilder();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject user = jsonArray.getJSONObject(i);
                    JSONObject username = user.getJSONObject("name");
                    String fullname = String.format("%s %S\n", username.get("first"), username.get("last"));
                    stringBuilderFullname.append(fullname);
                    Toast.makeText(this, stringBuilderFullname, Toast.LENGTH_SHORT).show();
//                    Log.e("TAG", fullname );
                }
                


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        } else if (v.getId() == R.id.quit) {
            finish();
        }
    }
}