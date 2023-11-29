package com.example.demoservice

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.session.MediaSession
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.demoservice.databinding.ActivityMainBinding
import com.example.demoservice.service.FirstService
import com.example.demoservice.service.SecondService


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val btnStartService = binding.btnStartService
        val btnStopService = binding.btnStopService

        btnStartService.setOnClickListener {
           clickStartService()
        }

        btnStopService.setOnClickListener {
            clickStopService()
        }


    }

    private fun clickStopService() {
        val intent = Intent(this, FirstService::class.java)
        stopService(intent)

    }

    private fun clickStartService() {
        val intent = Intent(this, FirstService::class.java)
        intent.putExtra("key", "Hello User")

        startService(intent)
    }


    private fun sendNotiFunc() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var builder = NotificationCompat.Builder(this, MyAplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("First noti")
            .setContentText("Hello")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(1, builder.build())

    }


}