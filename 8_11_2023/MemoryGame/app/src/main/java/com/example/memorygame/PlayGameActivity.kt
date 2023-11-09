package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memorygame.databinding.ActivityPlayGameBinding

class PlayGameActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityPlayGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}