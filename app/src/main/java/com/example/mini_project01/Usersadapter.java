package com.example.mini_project01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Usersadapter extends BaseAdapter {
    ArrayList<User> users;

    LayoutInflater inflater;

    public Usersadapter(Context context, ArrayList<User> users) {
        this.users = users;
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
        convertView = inflater.inflate(R.layout.layout_user_item, null);
        TextView tvUsersItemFullname = convertView.findViewById(R.id.tvuseritemFullname);
        TextView tvUsersItemCity = convertView.findViewById(R.id.tvuseritemCity);
        TextView tvUsersItemGender = convertView.findViewById(R.id.tvuseritemGender);

        tvUsersItemFullname.setText(users.get(position).fullname());
        tvUsersItemCity.setText(users.get(position).getCity());
        tvUsersItemGender.setText(users.get(position).getGender());


        return convertView;
    }
}