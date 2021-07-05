package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_Data extends AppCompatActivity {
    Button add;
    EditText name, phone, age, salary, doj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        name = findViewById(R.id.add_name);
        phone = findViewById(R.id.add_phone);
        age = findViewById(R.id.add_age);
        salary = findViewById(R.id.add_salary);
        doj = findViewById(R.id.add_doj);
        add = findViewById(R.id.add_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t_name = name.getText().toString();
                String t_phone = phone.getText().toString();
                String t_age = age.getText().toString();
                String t_salary = salary.getText().toString();
                String t_doj = doj.getText().toString();
                if (!t_name.isEmpty() && !t_phone.isEmpty() && !t_age.isEmpty() && !t_salary.isEmpty() && !t_doj.isEmpty()) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(Insert_Data.this);
                    User user = new User(t_name, t_phone, t_age, t_salary, t_doj);
                    databaseHandler.addUser(user);
                    name.setText("");
                    phone.setText("");
                    age.setText("");
                    salary.setText("");
                    doj.setText("");
                    Toast.makeText(Insert_Data.this, "Add Text Successfully!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}