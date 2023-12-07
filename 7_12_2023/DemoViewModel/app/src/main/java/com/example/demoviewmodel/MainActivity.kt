package com.example.demoviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.demoviewmodel.MViewModel.MainActivityViewModel
import com.example.demoviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val txtNum = binding.txtCount
        val btnClick = binding.btnClick

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.mCountNum.observe(this) {
            txtNum.text = it.toString()
        }

        btnClick.setOnClickListener{
            mainActivityViewModel.updateNum()
        }


    }
}