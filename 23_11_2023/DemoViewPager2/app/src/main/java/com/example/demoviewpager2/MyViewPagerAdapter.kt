package com.example.demoviewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyViewPagerAdapter(val fragmentList: ArrayList<Fragment>, fragmentManager: FragmentManager, lifecycle: Lifecycle) :  FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return  fragmentList[position]
    }

}