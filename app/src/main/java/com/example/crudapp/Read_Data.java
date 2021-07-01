package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Read_Data extends AppCompatActivity {
    ListView listView;
    ArrayList<User> arrayList = new ArrayList<>();
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        listView = findViewById(R.id.listview);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        arrayList = databaseHandler.getUserData();
//        Toast.makeText(this, (CharSequence) arrayList, Toast.LENGTH_SHORT).show();
        userAdapter = new UserAdapter(Read_Data.this, R.layout.user_list, arrayList);
        listView.setAdapter(userAdapter);
    }
}