package com.example.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String gymManagementSystem = "UserDatabase";
    private static final int dataBase_Version = 1;

    private String Table_User = "Users";
    private String user_id = "user_id";
    private String name = "user_name";
    private String phone = "user_phone";
    private String age = "user_age";
    private String salary = "user_salary";
    private String doj = "user_doj";

    public DatabaseHandler(@Nullable Context context) {
        super(context, gymManagementSystem, null, dataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable =
                "CREATE TABLE " + Table_User +
                        " (" + user_id + " INTEGER PRIMARY KEY NOT NULL, " + name + " TEXT, " + phone +
                        " TEXT, " + age +
                        " TEXT, " + salary +
                        " TEXT, " + doj +
                        " TEXT " + " )";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new StringBuilder().append("DROP TABLE IF EXISTS ").append(Table_User).toString());
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase(); //better to read/write in background thread, cuz its a long process

        ContentValues values = new ContentValues();
        values.put(name, user.getName());
        values.put(phone, user.getPhone());
        values.put(age, user.getAge());
        values.put(salary, user.getSalary());
        values.put(doj, user.getDoj());

        db.insert(Table_User, null, values);
        db.close();
    }

    public User getUserByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {user_id, name, phone, age, salary, doj};
        String selection = user_id + " =? "; // '=?' -> where
        String[] args = {String.valueOf(id)};
        Cursor cursor = db.query(Table_User, projection, selection, args, null, null, null);
        cursor.moveToNext();
        User user = new User();
        user.setId(cursor.getString(cursor.getColumnIndexOrThrow(user_id)));
        user.setName(cursor.getString(cursor.getColumnIndexOrThrow(name)));
        user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(phone)));
        user.setAge(cursor.getString(cursor.getColumnIndexOrThrow(age)));
        user.setSalary(cursor.getString(cursor.getColumnIndexOrThrow(salary)));
        user.setDoj(cursor.getString(cursor.getColumnIndexOrThrow(doj)));
        cursor.close();
        return user;
    }

    public ArrayList getUserData() {
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();  //not sure here, re-check
        String query = new StringBuilder().append("SELECT * FROM ").append(Table_User).toString();
        Cursor cursor = db.rawQuery(query, null); //raw query can only be used for read operations
        while (cursor.moveToNext()) { //iterate unless cursor is null.
            userList.add(new User(cursor.getString(cursor.getColumnIndexOrThrow(user_id)),
                    cursor.getString(cursor.getColumnIndexOrThrow(name)),
                    cursor.getString(cursor.getColumnIndexOrThrow(phone)),
                    cursor.getString(cursor.getColumnIndexOrThrow(age)),
                    cursor.getString(cursor.getColumnIndexOrThrow(salary)),
                    cursor.getString(cursor.getColumnIndexOrThrow(doj))));
        }
        cursor.close();
        return userList;
    }

    public int deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deleteCurrentValue = db.delete(Table_User, user_id + " =?", new String[]{id});
        db.close();
        return deleteCurrentValue;
    }

    public boolean updateUser(String iid, String n, String p, String a, String s, String d) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues data = new ContentValues();
        data.put(name, n);
        data.put(phone, p);
        data.put(age, a);
        data.put(salary, s);
        data.put(doj, d);
        db.update(Table_User, data, "user_id=" + iid, null);
        db.close();
        return true;
    }
}
