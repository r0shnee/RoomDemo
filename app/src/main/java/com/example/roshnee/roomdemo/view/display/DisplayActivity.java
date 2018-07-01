package com.example.roshnee.roomdemo.view.display;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.roshnee.roomdemo.database.MyAppDatabase;
import com.example.roshnee.roomdemo.helper.DatabaseHelper;
import com.example.roshnee.roomdemo.R;
import com.example.roshnee.roomdemo.repository.DisplayRepo;
import com.example.roshnee.roomdemo.repository.RegisterRepo;
import com.example.roshnee.roomdemo.view.register.RegisterActivity;
import com.example.roshnee.roomdemo.model.Users;
import com.example.roshnee.roomdemo.viewModel.DisplayVM;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class DisplayActivity extends AppCompatActivity implements OnClickListener {

    TextView message;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private List<Users> userList;
    private com.example.roshnee.roomdemo.view.display.UserAdapter adapter;
    DisplayVM displayVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initComponent();
        initListener();
        initRecyclerView();
    }

    public void initComponent() {
        message = findViewById(R.id.message);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerInformationList);

        displayVM = ViewModelProviders.of(this).get(DisplayVM.class);
        MyAppDatabase myAppDatabase = Room
                .databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb")
                .allowMainThreadQueries()
                .build();
        displayVM.setRepo(new DisplayRepo(myAppDatabase.userDao()));
    }

    public void initListener() {
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == fab) {
            Intent intent = new Intent(DisplayActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initRecyclerView() {

        userList = displayVM.readDataFromLocalDB();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (userList.isEmpty()) {
            message.setText("No data");
        }
        adapter = new UserAdapter(DisplayActivity.this, userList,displayVM);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

