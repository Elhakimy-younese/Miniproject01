package com.example.mini_project01;

import android.content.Context;
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


//        convertView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
////                AlertDialog.Builder builder = new AlertDialog.Builder(context);
////                builder.setTitle(String.format("Details of User %d", position + 1))
////                        .setMessage(user.toString())
////                        .show();
//
//                return false;
//            }
//        });

        convertView.setOnTouchListener(new View.OnTouchListener() {
            long onclick = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        onclick = System.currentTimeMillis();
                        break;

                    case MotionEvent.ACTION_UP:
                        long clickTime = System.currentTimeMillis();
                        if (clickTime - onclick >= 1000 && clickTime - onclick <= 2000) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle(String.format("Details of User %d", position + 1))
                                    .setMessage(user.toString())
                                    .show();
                        }
                        break;

                }



                return true;
            }
        });





        return convertView;
    }
}
