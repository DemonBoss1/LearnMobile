package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.auth.FirebaseAuth;

public class DoListActivity extends AppCompatActivity {

    public static final int NUM_PAGE = 3;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    Button Button_task_list;
    Button Button_add_task;
    Button Button_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_list);

        DataBase.getDataBase();
        TaskAdapter.activity = this;

        viewPager2 = findViewById(R.id.fragmentContainerView);
        pagerAdapter = new SlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

        Button_task_list = findViewById(R.id.button_task_list);
        Button_add_task = findViewById(R.id.button_add_task);
        Button_setting = findViewById(R.id.button_setting);
        Button_task_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(0);
            }
        });
        Button_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(1);
            }
        });
        Button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(2);
            }
        });

    }

    public void Exit(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}