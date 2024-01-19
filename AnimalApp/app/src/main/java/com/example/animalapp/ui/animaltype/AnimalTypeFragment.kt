package com.example.animalapp.ui.animaltype

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.animalapp.R
import com.example.animalapp.api.ApiService
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentAnimalTypeBinding
import com.example.animalapp.model.AnimalType
import com.example.animalapp.utils.Constants
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Animal Type Created Fragment

@AndroidEntryPoint
class AnimalTypeFragment : BaseFragment<FragmentAnimalTypeBinding>() {

    private val viewModel: AnimalTypeViewModel by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAnimalTypeBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.getAllAnimalType()
        observeModel()
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.txtMammal.text = it.data?.get(0)?.name.toString()
                    binding.txtBird.text = it.data?.get(1)?.name.toString()
                    binding.txtFish.text = it.data?.get(2)?.name.toString()
                    binding.txtReptiles.text = it.data?.get(3)?.name.toString()
                    binding.txtAmphibians.text = it.data?.get(4)?.name.toString()
                    binding.txtArthropods.text = it.data?.get(5)?.name.toString()
                    setOnclick(binding.txtMammal.text.toString())
                    binding.btnFish.setOnClickListener{
                        val bundle = Bundle().apply {
                            putString("animal_type", binding.txtFish.text.toString())
                        }
                        findNavController().navigate(R.id.action_animalTypeFragment_to_listAnimalFragment,bundle)
                    }
                    binding.btnBird.setOnClickListener{
                        val bundle = Bundle().apply {
                            putString("animal_type", binding.txtBird.text.toString())
                        }
                        findNavController().navigate(R.id.action_animalTypeFragment_to_listAnimalFragment,bundle)
                    }
                    binding.btnFish.setOnClickListener{
                        val bundle = Bundle().apply {
                            putString("animal_type", binding.txtFish.text.toString())
                        }
                        findNavController().navigate(R.id.action_animalTypeFragment_to_listAnimalFragment,bundle)
                    }
                    binding.btnAmphibians.setOnClickListener{
                        val bundle = Bundle().apply {
                            putString("animal_type", binding.txtAmphibians.text.toString())
                        }
                        findNavController().navigate(R.id.action_animalTypeFragment_to_listAnimalFragment,bundle)
                    }
                    binding.btnArthropods.setOnClickListener{
                        val bundle = Bundle().apply {
                            putString("animal_type", binding.txtArthropods.text.toString())
                        }
                        findNavController().navigate(R.id.action_animalTypeFragment_to_listAnimalFragment,bundle)
                    }
                    binding.btnReptiles.setOnClickListener{
                        val bundle = Bundle().apply {
                            putString("animal_type", binding.txtReptiles.text.toString())
                        }
                        findNavController().navigate(R.id.action_animalTypeFragment_to_listAnimalFragment,bundle)
                    }

                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    private fun setOnclick(name: String) {
        binding.btnMammals.setOnClickListener{
            val bundle = Bundle().apply {
                putString("animal_type", name)
            }
            findNavController().navigate(R.id.action_animalTypeFragment_to_listAnimalFragment,bundle)
        }

    }
}