package com.example.animalapp.ui.image_predict

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentResultInfoBinding


class ResultInfoFragment : BaseFragment<FragmentResultInfoBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentResultInfoBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        var imgUri: Uri ?= null
        imgUri = Uri.parse(args?.getString("img_uri"))

        imgUri?.let {
            binding.userImg.setImageURI(it)
        }

    }


}