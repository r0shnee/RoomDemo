package com.example.roshnee.roomdemo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.roshnee.roomdemo.dao.userDao;
import com.example.roshnee.roomdemo.model.Users;

@Database(entities = {Users.class}, version = 1, exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract userDao userDao();


}
