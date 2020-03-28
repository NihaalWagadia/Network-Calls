package com.example.ign_app.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ign_app.activities.ArticleTab;
import com.example.ign_app.activities.VideoTab;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.mNoOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ArticleTab articleTab = new ArticleTab();
                return articleTab;

            case 1:
                VideoTab videoTab = new VideoTab();
                return videoTab;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
