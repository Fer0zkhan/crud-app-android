package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_Data extends AppCompatActivity {
    Button add;
    EditText name, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        name = findViewById(R.id.add_name);
        phone = findViewById(R.id.add_phone);
        add = findViewById(R.id.add_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_name = name.getText().toString();
                String t_phone = phone.getText().toString();
                if (!t_name.isEmpty() && !t_phone.isEmpty()) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(Insert_Data.this);
                    User user = new User(t_name, t_phone);
                    databaseHandler.addUser(user);
                    name.setText("");
                    phone.setText("");
                    Toast.makeText(Insert_Data.this, "Add Text Successfully!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}