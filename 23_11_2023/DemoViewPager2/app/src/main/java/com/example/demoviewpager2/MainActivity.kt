package com.example.demoviewpager2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.demoviewpager2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentList = arrayListOf<Fragment>(HomeFragment(), VideoFragment(), NotiFragment())

        binding.apply {
            mViewPager.adapter = MyViewPagerAdapter(
                fragmentList,
                this@MainActivity.supportFragmentManager,
                lifecycle
            )
            TabLayoutMediator(mTabLayout, mViewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_home)
                    }
                    1 -> {
                        tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_video)
                    }
                    2 -> {
                        tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_noti)
                    }

                }

            }.attach()


        }
    }
}