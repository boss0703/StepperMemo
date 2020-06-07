package com.example.steppermemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.steppermemo.ui.home.HomeFragment
import com.example.steppermemo.ui.profile.ProfileFragment

class TabAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                HomeFragment()
            }
            else ->  {
                ProfileFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> {
                "home"
            }
            else ->  {
                "profile"
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}