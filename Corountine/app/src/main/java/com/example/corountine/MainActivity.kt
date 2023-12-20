package com.example.corountine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.corountine.firstcoroutines.corountinecontext.TestDispatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TestDispatcher.runMyFirstCoroutine()
    }
}