package com.example.crudapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter {
    ArrayList<User> arrayList = new ArrayList<>();
    Activity context;
    public UserAdapter(@NonNull Activity context, int resource, ArrayList<User> arrayList) {
        super(context, resource, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.user_list, null);

        TextView u_id, u_name, u_phone;

        u_id = (TextView) view.findViewById(R.id.u_id);
        u_name = (TextView) view.findViewById(R.id.u_name);
        u_phone = (TextView) view.findViewById(R.id.u_phone);

        u_id.setText(arrayList.get(position).getId());
        u_name.setText(arrayList.get(position).getName());
        u_phone.setText(arrayList.get(position).getPhone());
        return view;
    }
}
