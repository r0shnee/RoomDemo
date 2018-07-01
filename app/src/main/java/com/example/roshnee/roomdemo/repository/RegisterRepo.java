package com.example.roshnee.roomdemo.repository;

import com.example.roshnee.roomdemo.dao.userDao;
import com.example.roshnee.roomdemo.model.Users;

public class RegisterRepo {

    userDao userDao;
    Users users = new Users();

    public RegisterRepo(userDao userDao){
        this.userDao = userDao;
    }

    public void insertDataToLocalDB(String name, String address, String level){

        users.name = name;
        users.address = address;
        users.level = level;

        userDao.insertAll(users);
    }

   public Users getAndSetData(int id){

        return userDao.findById(id);
    }

    public void updateDataInLocalDB(int id, String name, String address, String level){

        users.id = id;
        users.name = name;
        users.address = address;
        users.level = level;

        userDao.update(users);
    }
}
