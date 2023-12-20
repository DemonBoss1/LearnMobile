package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class DoListActivity extends AppCompatActivity {

    public static final int NUM_PAGE = 2;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    Button Button_task_list;
    Button Button_add_task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_list);

        DataBase.getDataBase();

        viewPager2 = findViewById(R.id.fragmentContainerView);
        pagerAdapter = new SlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

        Button_task_list = findViewById(R.id.button_task_list);
        Button_add_task = findViewById(R.id.button_add_task);
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

    }
}