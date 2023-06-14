package com.example.mini_project01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
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
        ImageView checkedimage = convertView.findViewById(R.id.ivitemchecked);

        tvUsersItemFullname.setText(user.fullname());
        tvUsersItemCity.setText(user.getCity());


        View finalConvertView = convertView;
        convertView.setOnTouchListener(new OnSwipeTouchListener(context){

            @SuppressLint("ResourceAsColor")
            @Override
            public void swipeLeft() {
                finalConvertView.setBackgroundColor(Color.rgb(247, 235, 237));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(String.format("Details of User %d", position + 1))
                        .setMessage("Do you want to delete this user")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                users.remove(position);
                                notifyDataSetChanged();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finalConvertView.setBackgroundColor(Color.WHITE);
                            }
                        })
                        .show();



            }


        });



        return convertView;
    }


}
