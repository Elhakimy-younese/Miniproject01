package com.example.mini_project01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button loadusers, quit;
    RadioButton Males, Females;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadusers = findViewById(R.id.loaduser);
        quit = findViewById(R.id.quit);
        Males = findViewById(R.id.male);
        Females = findViewById(R.id.female);

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
                JSONArray jsonArray =jsonObject.getJSONArray("users") ;
                StringBuilder stringBuilderFullname = new StringBuilder();
                    String title = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject user = jsonArray.getJSONObject(i);


                        if (user.get("gender").equals("male") && Males.isChecked()) {
                            JSONObject username = user.getJSONObject("name");
                            String male = String.format("%s %S | %s\n", username.get("first"), username.get("last"), user.get("city"));
                            stringBuilderFullname.append(male);
                            title = "Male Users";
                        }
                        else if (user.get("gender").equals("female") && Females.isChecked()) {
                            JSONObject username = user.getJSONObject("name");
                            String female = String.format("%s %S | %s\n", username.get("first"), username.get("last"), user.get("city"));
                            stringBuilderFullname.append(female);
                            title = "Female Users";
                        }
                    }

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle(title);
                    alertDialog.setMessage(stringBuilderFullname);
                    alertDialog.show();



            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        } else if (v.getId() == R.id.quit) {
            finish();
        }
    }
}