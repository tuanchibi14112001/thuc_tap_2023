package com.example.demoservice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SecondService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "On Created 2")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "On Start 2")
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        Log.d("TAG", "On Deleted 2")
        super.onDestroy()
    }

}