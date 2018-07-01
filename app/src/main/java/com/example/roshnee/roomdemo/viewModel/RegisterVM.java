package com.example.roshnee.roomdemo.viewModel;

import android.arch.lifecycle.ViewModel;

import com.example.roshnee.roomdemo.model.Users;
import com.example.roshnee.roomdemo.repository.RegisterRepo;

public class RegisterVM extends ViewModel {

    RegisterRepo registerRepo;

    public void setRepo(RegisterRepo registerRepo){
        this.registerRepo = registerRepo;
    }

    public void insertDataToLocalDB(String name, String address, String level){

        registerRepo.insertDataToLocalDB(name, address, level);

    }

   public Users getAndSetData(int id){
        return registerRepo.getAndSetData(id);
    }

    public void updateDataInLocalDB(int id, String name, String address, String level){

        registerRepo.updateDataInLocalDB(id, name, address, level);

    }
}
