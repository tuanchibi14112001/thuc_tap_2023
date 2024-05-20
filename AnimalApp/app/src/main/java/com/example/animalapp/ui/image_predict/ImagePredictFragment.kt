package com.example.animalapp.ui.image_predict

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentImagePredictBinding
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImagePredictFragment : BaseFragment<FragmentImagePredictBinding>() {
    private val viewModel: ImagePredictViewModel by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentImagePredictBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.btnNext.setOnClickListener{
            findNavController().navigate(R.id.action_imagePredictFragment_to_loginFragment)
        }
        viewModel.getTest()
        observeModel()
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.btnNext.text = it.data?.detail
                    Log.d("CHECK", it.data.toString())
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    it.message?.let { it1 -> Log.d("CHECK", it1) }
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }

}