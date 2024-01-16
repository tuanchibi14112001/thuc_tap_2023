package com.example.animalapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animalapp.R
import com.example.animalapp.base.BaseActivity
import com.example.animalapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
    }

}