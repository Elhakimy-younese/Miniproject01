package com.example.mini_project01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class Usersadapter extends BaseAdapter {



    Context context;
    ArrayList<User> users;

    LayoutInflater inflater;
    FragmentManager fragmentManager;


    public Usersadapter(Context context, ArrayList<User> users, FragmentManager fragmentManager) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragmentManager = fragmentManager;

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
        Button Details = convertView.findViewById(R.id.btndetails);

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

        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsDialog userDetailsDialog = new UserDetailsDialog(user);
                userDetailsDialog.show(fragmentManager, null);
            }
        });

        convertView.setOnTouchListener(new View.OnTouchListener() {
            long lastClickTime = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_UP:
                        long clickTime = System.currentTimeMillis();
                        long DOUBLE_CLICK_TIMEOUT = 250;
                        if (clickTime - lastClickTime <= DOUBLE_CLICK_TIMEOUT) {
                            checkedimage.setVisibility(checkedimage.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE);

                        }else
                            lastClickTime = clickTime;
                        break;

                }

                return true;
            }
        });





        return convertView;
    }
}
