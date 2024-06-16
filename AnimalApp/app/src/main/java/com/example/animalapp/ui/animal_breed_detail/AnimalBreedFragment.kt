package com.example.animalapp.ui.animal_breed_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentAnimalBreedBinding
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnimalBreedFragment : BaseFragment<FragmentAnimalBreedBinding>() {
    private val viewModel: AnimalBreedViewModel by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnimalBreedBinding {
        return FragmentAnimalBreedBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val animalBreedItemId = args?.getInt("animal_breed_detail")
        if(animalBreedItemId != null){
            viewModel.getAnimalSpecies(animalBreedItemId)
        }
        observeModel()
//        Log.d("CHECK", animalBreedItem.toString())

    }

    private fun bindView(animalBreedItem: AnimalBreedItem?) {
        binding.txtName.text = animalBreedItem?.name
        binding.imgAnimal.load(animalBreedItem?.img_url)
        binding.descName.text = animalBreedItem?.name
        binding.descLength.text = animalBreedItem?.animal_length
        binding.descComment.text = animalBreedItem?.comments
        binding.descLife.text = animalBreedItem?.average_lifespan
        binding.descWeight.text = animalBreedItem?.animal_weight
        binding.descTail.text = animalBreedItem?.animal_tail
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    bindView(it.data)
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