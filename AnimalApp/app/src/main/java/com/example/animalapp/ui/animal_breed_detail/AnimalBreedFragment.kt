package com.example.animalapp.ui.animal_breed_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentAnimalBreedBinding
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.utils.MySharedPreferences
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnimalBreedFragment : BaseFragment<FragmentAnimalBreedBinding>() {
    private val viewModel: AnimalBreedViewModel by viewModels()
    private var is_liked: Boolean = false

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnimalBreedBinding {
        return FragmentAnimalBreedBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val animalBreedItemId = args?.getInt("animal_breed_detail")
        val mySharedPreferences = MySharedPreferences(requireContext())
        val token = mySharedPreferences.getUserToken()

        if (animalBreedItemId != null) {
            if (token != "null" && token != "") {
                viewModel.getAnimalBreedDetail(token, animalBreedItemId)
                observeModel(token)
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }


//        Log.d("CHECK", animalBreedItem.toString())

    }

    private fun bindView(animalBreedItem: AnimalBreedItem, token: String) {
        binding.txtName.text = animalBreedItem.name
        binding.imgAnimal.load(animalBreedItem.img_url)
        binding.descName.text = animalBreedItem.name
        binding.descLength.text = animalBreedItem.animal_length
        binding.descComment.text = animalBreedItem.comments
        binding.descLife.text = animalBreedItem.average_lifespan
        binding.descWeight.text = animalBreedItem.animal_weight
        binding.descTail.text = animalBreedItem.animal_tail
    }


    private fun bindLikeBtn(token: String, id: Int) {

        binding.floatingActionButton2.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                R.color.red
            )
        )
        binding.floatingActionButton2.setOnClickListener {
            viewModel.postUnlikeBreed(token, id)
            observeUnlikeDataModel(token, id)
        }
    }

    private fun bindUnlikeBtn(token: String, id: Int) {

        binding.floatingActionButton2.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding.floatingActionButton2.setOnClickListener {
            viewModel.postLikeBreed(token, id)
            observeLikeDataModel(token, id)
        }
    }


    private fun observeModel(token: String) {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { animalBreedItem ->
                        is_liked = animalBreedItem.is_liked
                        bindView(animalBreedItem, token)
                        if (is_liked)
                            bindLikeBtn(token, animalBreedItem.id)
                        else
                            bindUnlikeBtn(token, animalBreedItem.id)
                    }
//                    Log.d("CHECK", it.data.toString())
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


    private fun observeLikeDataModel(token: String, id: Int) {
        viewModel.likeDataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        Toast.makeText(requireContext(), response.result, Toast.LENGTH_SHORT).show()
                        if (response.status) {
                            is_liked = !is_liked
                            bindLikeBtn(token, id)
                        }
                    }
//                    Log.d("CHECK", it.data.toString())
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

    private fun observeUnlikeDataModel(token: String, id: Int) {
        viewModel.unlikeDataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { response ->
                        Toast.makeText(requireContext(), response.result, Toast.LENGTH_SHORT).show()
                        if (response.status) {
                            is_liked = !is_liked
                            bindUnlikeBtn(token, id)
                        }
                    }
//                    Log.d("CHECK", it.data.toString())
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
}