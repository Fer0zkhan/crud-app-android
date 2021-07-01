package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update_data extends AppCompatActivity {
    Button search, update;
    EditText name, phone, search_id;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        name = findViewById(R.id.update_name);
        phone = findViewById(R.id.update_phone);
        search_id = findViewById(R.id.u_search);
        search = findViewById(R.id.update_btn_s);
        update = findViewById(R.id.update_btn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!search_id.getText().toString().isEmpty()) {
                    int i = Integer.parseInt(search_id.getText().toString());
                    user = databaseHandler.getUserByID(i);
                    name.setVisibility(View.VISIBLE);
                    phone.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);
                    name.setText(user.getName());
                    phone.setText(user.getPhone());
                    search_id.setText("");
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler.updateUser(user.getId(), name.getText().toString(), phone.getText().toString());

                name.setVisibility(View.GONE);
                phone.setVisibility(View.GONE);
                update.setVisibility(View.GONE);
            }
        });
    }
}