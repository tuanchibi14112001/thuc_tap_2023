package com.example.animalapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.btnInfo.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_animalTypeFragment)
        }
        binding.btnGame.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_memoryGameFragment)
        }
    }
}
