package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SlidePageAdapter extends FragmentStateAdapter {
    public SlidePageAdapter(DoListActivity doListActivity) {
        super(doListActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TaskListFragment();
            case 1:
                return new AddTaskFragment();
            case 2:
                return new SettingFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return DoListActivity.NUM_PAGE;
    }
}
