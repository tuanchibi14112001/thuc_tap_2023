package com.example.animalapp.ui.gallery.gallery_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentImageFullBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageFullFragment : BaseFragment<FragmentImageFullBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentImageFullBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        arguments?.let {
            val img_url = it.getString("img_url")
            binding.imgZoom.load(img_url){
                error(R.drawable.ques_mark)
            }
            binding.btnBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

}