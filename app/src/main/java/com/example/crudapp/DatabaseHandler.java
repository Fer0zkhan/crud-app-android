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

    public DatabaseHandler(@Nullable Context context) {
        super(context, gymManagementSystem, null, dataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable =
                "CREATE TABLE " + Table_User +
                        " (" + user_id + " INTEGER PRIMARY KEY NOT NULL, " + name + " TEXT, " + phone +
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

        db.insert(Table_User, null, values);
        db.close();
    }

    public User getUserByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {user_id, name, phone};
        String selection = user_id + " =? "; // '=?' -> where
        String[] args = {String.valueOf(id)};
        Cursor cursor = db.query(Table_User, projection, selection, args, null, null, null);
        cursor.moveToNext();
        User user = new User();
        user.setId(cursor.getString(cursor.getColumnIndexOrThrow(user_id)));
        user.setName(cursor.getString(cursor.getColumnIndexOrThrow(name)));
        user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(phone)));
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
                    cursor.getString(cursor.getColumnIndexOrThrow(phone))));
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

    public boolean updateUser(String iid, String n, String p) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues data=new ContentValues();
        data.put(name, n);
        data.put(phone, p);
        db.update(Table_User, data, "user_id=" + iid, null);
        db.close();
        return true;
    }
}
