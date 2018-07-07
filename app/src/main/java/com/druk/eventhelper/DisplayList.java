package com.druk.eventhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayList extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Event> arrayList=new ArrayList<>();
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        recyclerView=findViewById(R.id.recyclerView);
        Intent intent=getIntent();
        user_id = intent.getStringExtra("id");

        BackgroundTask backgroundTask=new BackgroundTask(DisplayList.this,user_id);
        backgroundTask.getList(new BackgroundTask.arrayCallBack() {
            @Override
            public void onSuccess(ArrayList<Event> events) {
                adapter = new RecyclerAdapter(events);
                recyclerView.setAdapter(adapter);
                layoutManager=new LinearLayoutManager(DisplayList.this);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFail(String msg) {
                // Do Stuff
            }
        });


    }
}
