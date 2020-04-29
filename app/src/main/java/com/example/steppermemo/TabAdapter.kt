package com.example.steppermemo

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.steppermemo.ui.home.HomeFragment
import com.example.steppermemo.ui.profile.ProfileFragment

class TabAdapter(fm: FragmentManager, private val context: Context): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> { return HomeFragment() }
            else ->  { return ProfileFragment() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> { return "home" }
            else ->  { return "profile" }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}