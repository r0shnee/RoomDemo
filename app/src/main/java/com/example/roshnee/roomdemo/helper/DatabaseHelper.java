package com.example.roshnee.roomdemo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.roshnee.roomdemo.model.Users;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static int DB_VERSION = 3;
    public static final String DATABASE_NAME = "DemoDatabase";
    public static final String TABLE_NAME = "DemoTable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "ADDRESS";
    public static final String COL_4 = "LEVEL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String address, String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, level);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public ArrayList<Users> getData() {
        ArrayList<Users> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()) {
            Users user = new Users();

            user.id = cursor.getInt(cursor.getColumnIndex(COL_1));
            user.name = cursor.getString(cursor.getColumnIndex(COL_2));
            user.address = cursor.getString(cursor.getColumnIndex(COL_3));
            user.level = cursor.getString(cursor.getColumnIndex(COL_4));

            users.add(user);
        }

        cursor.close();
        return users;
    }


    public Users getData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + COL_1 + " = '" + id + "';";
        //Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        Users user = new Users();
        //user.id = cursor.getInt(cursor.getColumnIndex(COL_1));
        user.name = cursor.getString(cursor.getColumnIndex(COL_2));
        user.address = cursor.getString(cursor.getColumnIndex(COL_3));
        user.level = cursor.getString(cursor.getColumnIndex(COL_4));

        cursor.close();

        return user;
    }

    public boolean deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "id = ?";
        String[] whereArgs = new String[]{id + ""};
        int result = db.delete(TABLE_NAME, where, whereArgs);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean updateData(int id, String name, String address, String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, level);
        String where = "id = ?";
        String[] whereArgs = new String[]{id + ""};
        long result = db.update(TABLE_NAME, contentValues, where, whereArgs);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


}
