package com.example.demoactivity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.demoactivity.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null
    var continueMusic: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG", "Activity A CREATE")
        this.mediaPlayer = MediaPlayer.create(this, R.raw.music)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            // xoay ngang man hinh van nghe tiep
            this.continueMusic = savedInstanceState.getInt("position")
            this.mediaPlayer?.seekTo(continueMusic)
            this.mediaPlayer?.start()
        } else {
            val i = intent
            this.continueMusic = i.getIntExtra("position",0) // Lay vi tri hien tai tu B truyen sang
            this.mediaPlayer?.seekTo(continueMusic)
            this.mediaPlayer?.start()
        }


        binding.buttonB.setOnClickListener {
            val intentB = Intent(this, ActivityB::class.java)
            this.continueMusic = this.mediaPlayer?.currentPosition?:0
            intentB.putExtra("position", this.continueMusic) // Truyen cho B vi tri hien tai
            startActivity(intentB)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "Activity A START")
    }

    override fun onResume() {
        this.mediaPlayer?.start() // Bam nut lui lai khi dang o Activity B
        super.onResume()
        Log.d("TAG", "Activity A RESUME")

    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "Activity A PAUSE")
        this.mediaPlayer?.pause()
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "Activity A STOP")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        this.mediaPlayer?.let { it.currentPosition }?.let { outState.putInt("position", it) }
        super.onSaveInstanceState(outState)
    }



    override fun onDestroy() {
        this.mediaPlayer?.stop()
        super.onDestroy()
        Log.d("TAG", "Activity A DESTROY")
    }

}