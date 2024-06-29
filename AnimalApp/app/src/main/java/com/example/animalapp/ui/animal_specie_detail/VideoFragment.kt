package com.example.animalapp.ui.animal_specie_detail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.MediaController
import androidx.navigation.fragment.findNavController
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentVideoBinding

class VideoFragment : BaseFragment<FragmentVideoBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentVideoBinding.inflate(inflater, container, false)


    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
        args?.let {
            val videoUrl = it.getString("video_url")
            videoUrl?.let { url ->
                val uri = Uri.parse(url)
                binding.videoView.setVideoURI(uri)
//        val mediaController = object : MediaController(requireContext()){
//            override fun hide() {
//            }
//        }
                val mediaController = MediaController(requireContext())
                binding.videoView.setMediaController(mediaController)
                mediaController.setAnchorView(binding.videoView)
                binding.videoView.start()
            }
        }
    }


}