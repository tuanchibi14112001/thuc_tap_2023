package com.example.demoservice.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val actionMusic = intent?.getIntExtra("ACTION_MUSIC", 0)
        val intentService = Intent(context, FirstService::class.java)
        Log.d("CHECK", actionMusic.toString() + " 4")
        intentService.putExtra("ACTION_MUSIC_SERVICE", actionMusic)
        context?.startService(intentService)
    }
}