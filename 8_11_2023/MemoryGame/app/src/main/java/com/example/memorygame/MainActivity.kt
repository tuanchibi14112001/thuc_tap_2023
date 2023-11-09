package com.example.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memorygame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener{
            val intent = Intent(this, PlayGameActivity::class.java)
            startActivity(intent)
        }
    }
}