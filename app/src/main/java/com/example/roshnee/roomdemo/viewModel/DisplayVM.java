package com.example.roshnee.roomdemo.viewModel;

import android.arch.lifecycle.ViewModel;

import com.example.roshnee.roomdemo.model.Users;
import com.example.roshnee.roomdemo.repository.DisplayRepo;
import com.example.roshnee.roomdemo.repository.RegisterRepo;

import java.util.ArrayList;
import java.util.List;

public class DisplayVM extends ViewModel {

    DisplayRepo displayRepo;

    public void setRepo(DisplayRepo displayRepo){
        this.displayRepo = displayRepo;
    }

    public List<Users> readDataFromLocalDB(){
        return displayRepo.readDataFromLocalDB();
    }

    public void deleteDataFromLocalDB(int id){
        displayRepo.deleteDataFromLocalDB(id);
    }




}
