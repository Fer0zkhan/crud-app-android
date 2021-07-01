package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Delete_User extends AppCompatActivity {
    Button search, del_btn;
    TextView name, phone;
    EditText id_search;
    DatabaseHandler databaseHandler = new DatabaseHandler(this);
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        id_search = findViewById(R.id.del_search);
        name = findViewById(R.id.del_name);
        phone = findViewById(R.id.del_phone);
        search = findViewById(R.id.del_s_btn);
        del_btn = findViewById(R.id.del_btn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id_search.getText().toString().isEmpty()) {
                    int i = Integer.parseInt(id_search.getText().toString());
                    user = databaseHandler.getUserByID(i);
                    name.setText(user.getName());
                    phone.setText(user.getPhone());
                    del_btn.setVisibility(View.VISIBLE);
                }
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler.deleteUser(user.getId());
                id_search.setText("");
                name.setText("");
                phone.setText("");
                del_btn.setVisibility(View.GONE);
            }
        });
    }
}