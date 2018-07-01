package com.example.roshnee.roomdemo.view.register;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roshnee.roomdemo.database.MyAppDatabase;
import com.example.roshnee.roomdemo.helper.DatabaseHelper;
import com.example.roshnee.roomdemo.R;
import com.example.roshnee.roomdemo.model.Users;
import com.example.roshnee.roomdemo.repository.DisplayRepo;
import com.example.roshnee.roomdemo.repository.RegisterRepo;
import com.example.roshnee.roomdemo.view.display.DisplayActivity;
import com.example.roshnee.roomdemo.viewModel.RegisterVM;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    EditText name, address, level;
    Button register;
    RegisterVM registerVM;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ID = getIntent().getIntExtra("ID", -1);
        initComponent();
        initListener();
        setData();
    }

    private void initComponent() {
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        level = findViewById(R.id.level);
        register = findViewById(R.id.registerBtn);

        registerVM = ViewModelProviders.of(this).get(RegisterVM.class);
        MyAppDatabase myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb")
                .allowMainThreadQueries()
                .build();
        registerVM.setRepo(new RegisterRepo(myAppDatabase.userDao()));

    }

    private void initListener() {
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == register) {

            registerVM.insertDataToLocalDB(name.getText().toString(),
                    address.getText().toString(),
                    level.getText().toString());


            name.setText("");
            address.setText("");
            level.setText("");

            Intent intent = new Intent(RegisterActivity.this, DisplayActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void setData() {
        if (ID != -1) {
            register.setText("UPDATE");

            Users users = registerVM.getAndSetData(ID);

            String userName = users.name;
            String userAddress = users.address;
            String userLevel = users.level;

            name.setText(userName);
            address.setText(userAddress);
            level.setText(userLevel);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    registerVM.updateDataInLocalDB(ID, name.getText().toString(),
                            address.getText().toString(),
                            level.getText().toString());

                    Intent intent = new Intent(RegisterActivity.this, DisplayActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}




