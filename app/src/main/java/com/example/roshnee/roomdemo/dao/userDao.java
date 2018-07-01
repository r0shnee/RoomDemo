package com.example.roshnee.roomdemo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.roshnee.roomdemo.model.Users;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface userDao {

    @Insert
    void insertAll(Users user);

    @Query("SELECT * FROM usertable")
    List<Users> getAll();

    @Delete
    void delete(Users user);

    @Query("SELECT * FROM usertable where id = :userId")
    Users findById(int userId);

   @Update
    void update(Users users);

}

