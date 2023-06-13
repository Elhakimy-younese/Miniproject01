package com.example.mini_project01;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class Usersadapter extends BaseAdapter {
    Context context;
    ArrayList<User> users;

    LayoutInflater inflater;



    public Usersadapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = users.get(position);
        convertView = inflater.inflate(R.layout.layout_user_item, null);
        TextView tvUsersItemFullname = convertView.findViewById(R.id.tvuseritemFullname);
        TextView tvUsersItemCity = convertView.findViewById(R.id.tvuseritemCity);
        ImageButton btnDetails = convertView.findViewById(R.id.imageButton);

        tvUsersItemFullname.setText(user.fullname());
        tvUsersItemCity.setText(user.getCity());
        btnDetails.setOnClickListener(v -> {
            Intent intent = new Intent(context, User_Details.class);
            intent.putExtra("firstname", user.getFirstname());
            intent.putExtra("lastname", user.getLastname());
            intent.putExtra("city", user.getCity());
            intent.putExtra("gender", user.getGender());
            context.startActivity(intent);
        });


        return convertView;
    }
}
