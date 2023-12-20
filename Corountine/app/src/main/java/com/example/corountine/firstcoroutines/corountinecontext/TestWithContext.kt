package com.example.corountine.firstcoroutines.corountinecontext

import android.util.Log
import com.example.corountine.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object TestWithContext {
    fun testFirstWithContextFunc(){
        GlobalScope.launch(Dispatchers.IO) {
            // Run long time task
            Log.d(MainActivity::class.java.simpleName, "Run long time task - Thread - ${Thread.currentThread().name}")
            delay(2000L)
            withContext(Dispatchers.Main){
                // Update UI here
                Log.d(MainActivity::class.java.simpleName, "UI Thread - ${Thread.currentThread().name}")
            }
        }
    }
}