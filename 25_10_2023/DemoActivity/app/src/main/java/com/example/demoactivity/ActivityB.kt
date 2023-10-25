package com.example.demoactivity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.demoactivity.databinding.ActivityBBinding

private lateinit var binding: ActivityBBinding
class ActivityB : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        // Xoay ngang reset lai bai hat

        super.onCreate(savedInstanceState)
        Log.d("TAG", "Activity B CREATE")
        this.mediaPlayer= MediaPlayer.create(this, R.raw.musicb)
        this.mediaPlayer?.start()
        binding = ActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val continuePositionA: Int = i.getIntExtra("position",0)


        binding.buttonA.setOnClickListener{
            val intentA = Intent(this,MainActivity::class.java)
            intentA.putExtra("position",continuePositionA)
            startActivity(intentA)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "Activity B START")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "Activity B RESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "Activity B PAUSE")
    }

    override fun onStop() {
        super.onStop()

        Log.d("TAG", "Activity B STOP")
    }

    override fun onDestroy() {
        this.mediaPlayer?.stop()
        super.onDestroy()
        Log.d("TAG", "Activity B DESTROY")
    }
}