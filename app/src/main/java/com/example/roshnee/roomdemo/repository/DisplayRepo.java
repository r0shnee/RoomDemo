package com.example.roshnee.roomdemo.repository;
import android.content.Intent;

import com.example.roshnee.roomdemo.dao.userDao;
import com.example.roshnee.roomdemo.helper.DatabaseHelper;
import com.example.roshnee.roomdemo.model.Users;

import java.util.ArrayList;
import java.util.List;

public class DisplayRepo {

    userDao userDao;

    public DisplayRepo(userDao userDao){
        this.userDao = userDao;
    }



    public List<Users> readDataFromLocalDB() {
        return userDao.getAll();
    }

    public void deleteDataFromLocalDB(int id){

        Users user = new Users();
        user.id = id;
        userDao.delete(user);
    }


}
