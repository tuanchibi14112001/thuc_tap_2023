package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workmanager.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getIntExtra(RESULT, 0)
        binding.txtResult.text = result.toString()
    }
}