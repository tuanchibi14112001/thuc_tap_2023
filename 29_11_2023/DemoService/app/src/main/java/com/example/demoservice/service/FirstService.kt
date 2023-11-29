package com.example.demoservice.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.demoservice.MainActivity
import com.example.demoservice.MyAplication
import com.example.demoservice.R

class FirstService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "CREATE")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "START")
        val strDataIntent = intent?.getStringExtra("key")
        musicNotiFunc()
        return START_NOT_STICKY;
    }

    private fun sendNotiFunc(strDataIntent: String?) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, MyAplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Service Notification!")
            .setContentText(strDataIntent)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification )
    }

    private fun musicNotiFunc() {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mediaSessionCompat = MediaSessionCompat(this,"tag")
        val albumArtBitmap = BitmapFactory.decodeResource(resources, R.drawable.img_music)

        val notification = NotificationCompat.Builder(this, MyAplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_music)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_prev, "Previous", null) // #0
            .addAction(R.drawable.ic_pause, "Pause", null) // #1
            .addAction(R.drawable.ic_next, "Next", null) // #2
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0,1,2 /* #1: pause button \*/)
                .setMediaSession(mediaSessionCompat.sessionToken))
            .setContentTitle("Wonderful music")
            .setContentText("My Awesome Band")
            .setLargeIcon(albumArtBitmap)


        startForeground(1, notification.build())

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        Log.d("TAG", "DELETEG")
        super.onDestroy()
    }

}