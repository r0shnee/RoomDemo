package com.example.roshnee.roomdemo.view.display;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.roshnee.roomdemo.R;
import com.example.roshnee.roomdemo.helper.DatabaseHelper;
import com.example.roshnee.roomdemo.model.Users;
import com.example.roshnee.roomdemo.view.register.RegisterActivity;
import com.example.roshnee.roomdemo.viewModel.DisplayVM;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    DatabaseHelper myDb;
    Context context;
    List<Users> userList;
    DisplayVM displayVM;

    public UserAdapter(Context context, List<Users> userList,DisplayVM displayVM) {
        this.context = context;
        this.userList = userList;
        this.displayVM = displayVM;
        myDb = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view_information, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {


        final Users user = userList.get(position);

        holder.name.setText("Name: " + user.getName());
        holder.address.setText("Address : " + user.getAddress());
        holder.level.setText("Level :" + user.getLevel());

       holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = user.getId();


               displayVM.deleteDataFromLocalDB(id);
                userList.remove(user);
                notifyDataSetChanged();

            }
        });

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterActivity.class);
                intent.putExtra("ID", userList.get(position).getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView name, address, level;
        Button deleteBtn, updateBtn;

        public UserViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            level = itemView.findViewById(R.id.level);
            deleteBtn = itemView.findViewById(R.id.deletBtn);
            updateBtn = itemView.findViewById(R.id.updateBtn);

        }
    }
}



