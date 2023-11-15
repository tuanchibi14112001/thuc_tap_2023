package com.example.demointent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.demointent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.video)
        binding.video.setMediaController(mediaController)
        binding.video.requestFocus()
        binding.video.start()

        binding.btnStart.setOnClickListener {
            startVideo(it)

        }


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        when (intent?.action) {
            Intent.ACTION_SEND
            -> {
                Log.i("TAG", "0")
                if (intent.type?.startsWith("image/") == true)
                    handleSendImage(intent)
                else if (intent.type?.startsWith("video/") == true)
                    handleSendVideo(intent)

            }
        }

    }

    private fun startVideo(it: View?) {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        var putExtra = intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10)
        if (intent.resolveActivity(packageManager) != null)
            startActivityForResult(intent, 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            val videoUri = data?.data

            binding.video.setVideoURI(videoUri)

        }
    }

    private fun handleSendVideo(intent: Intent) {
        Log.i("TAG", "2")
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            binding.video.setVideoURI(it)


        }
    }

    private fun handleSendImage(intent: Intent) {
        Log.i("TAG", "1")
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            binding.imgApp.setImageURI(it)
        }


    }
}