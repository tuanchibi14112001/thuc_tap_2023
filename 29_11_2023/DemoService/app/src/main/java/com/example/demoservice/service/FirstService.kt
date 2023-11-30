package com.example.demoservice.service

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.demoservice.MainActivity
import com.example.demoservice.MyAplication
import com.example.demoservice.R
import com.example.demoservice.model.Song
import com.example.demoservice.utils.getSerializableCompat

class FirstService : Service() {

    companion object {
        private val ACTION_PLAY = 1
        private val ACTION_PAUSE = 2
        private val ACTION_RESUME = 3
        private val ACTION_CLEAR = 4

    }

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying: Boolean = false
    private var myReceiver = MyReceiver()
    private lateinit var mMusic: Song


    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "CREATE")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent!!.getIntExtra("ACTION_MUSIC_SERVICE", 0)
        Log.d("CHECK", action.toString() + " 1")
        if (action != 0)
            handleActionMusic(action)
        else {
            val bundle = intent?.extras
            Log.d("CHECK", bundle.toString())
            if (bundle != null) {
                val recvMusic: Song? = bundle.getSerializableCompat("OBJECT_SONG", Song::class.java)
                if (recvMusic != null)
                    mMusic = recvMusic
            }
            startMusic(mMusic)

        }




        return START_NOT_STICKY;
    }

    private fun handleActionMusic(action: Int) {
        when (action) {
            ACTION_PAUSE -> {
                pauseMusic()
            }

            ACTION_RESUME -> {
                resumeMusic()
            }

            ACTION_CLEAR -> {
                stopSelf()

            }
        }
    }

    private fun startMusic(mMusic: Song) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(applicationContext, mMusic.resource)
        }
        mediaPlayer!!.start()
        isPlaying = true
        musicNotiFunc(mMusic)

    }

    private fun pauseMusic() {
        if (mediaPlayer != null && isPlaying) {
            mediaPlayer!!.pause()
            isPlaying = false
            musicNotiFunc(mMusic)
        }

    }


    private fun resumeMusic() {
        if (mediaPlayer != null && !isPlaying) {
            mediaPlayer!!.start()
            isPlaying = true
            musicNotiFunc(mMusic)
        }

    }

    private fun getPendingIntent(context: Context, action: Int): PendingIntent {
        val intent = Intent(this, MyReceiver::class.java)
        Log.d("CHECK", action.toString() + " 2")
        intent.putExtra("ACTION_MUSIC", action)
        return PendingIntent.getBroadcast(
            context.applicationContext,
            action,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }


    private fun musicNotiFunc(song: Song) {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mediaSessionCompat = MediaSessionCompat(this, "tag")
        val albumArtBitmap = BitmapFactory.decodeResource(resources, song.image)

        val musicNotiBuilder = NotificationCompat.Builder(this, MyAplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_music).setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(1, 3 /* #1: pause button \*/)
            ).setContentTitle(song.title).setContentText(song.singer).setLargeIcon(albumArtBitmap)

        if (isPlaying) {
            musicNotiBuilder
                .addAction(R.drawable.ic_prev, "Previous", null) // #0
                .addAction(R.drawable.ic_pause, "Pause", getPendingIntent(this, ACTION_PAUSE)) // #1
                .addAction(R.drawable.ic_next, "Next", null) // #2
                .addAction(R.drawable.ic_clear, "Clear", getPendingIntent(this, ACTION_CLEAR)) // #3
        } else {
            musicNotiBuilder
                .addAction(R.drawable.ic_prev, "Previous", null) // #0
                .addAction(R.drawable.ic_play, "Play", getPendingIntent(this, ACTION_RESUME))  // #1
                .addAction(R.drawable.ic_next, "Next", null) // #2
                .addAction(R.drawable.ic_clear, "Clear", getPendingIntent(this, ACTION_CLEAR)) // #3
        }



        startForeground(1, musicNotiBuilder.build())

    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
        super.onDestroy()
    }

}