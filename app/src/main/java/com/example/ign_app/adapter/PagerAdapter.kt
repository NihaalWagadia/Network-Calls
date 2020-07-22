package com.example.ign_app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.ign_app.activities.ArticleTab
import com.example.ign_app.activities.VideoTab

//import com.example.ign_app.activities.VideoTab

//import com.example.ign_app.activities.VideoTab

class PagerAdapter(fm: FragmentManager, var mNoOfTabs: Int) : FragmentStatePagerAdapter(fm, mNoOfTabs) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ArticleTab()
            }
            else -> {
                VideoTab()
            }
        }
    }

    override fun getCount(): Int {
        return mNoOfTabs
    }

}