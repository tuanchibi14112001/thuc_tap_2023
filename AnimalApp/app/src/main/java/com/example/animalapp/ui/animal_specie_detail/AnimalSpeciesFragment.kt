package com.example.animalapp.ui.animal_specie_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentAnimalSpeciesBinding
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnimalSpeciesFragment : BaseFragment<FragmentAnimalSpeciesBinding>() {
    private val viewModel: AnimalSpeciesViewModel by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnimalSpeciesBinding {
        return FragmentAnimalSpeciesBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val animalSpecieItemId = args?.getInt("animal_species_detail")
        if(animalSpecieItemId != null){
            viewModel.getAnimalSpecies(animalSpecieItemId)
        }
        observeModel()
//        Log.d("CHECK", animalSpecieItem.toString())

    }

    private fun bindView(animalSpecieItem: AnimalSpecieItem?) {
        binding.txtName.text = animalSpecieItem?.name
        binding.imgAnimal.load(animalSpecieItem?.img_url)
        binding.descName.text = animalSpecieItem?.name
        binding.descLength.text = animalSpecieItem?.animal_length
        binding.descComment.text = animalSpecieItem?.comments
        binding.descLife.text = animalSpecieItem?.average_lifespan
        binding.descWeight.text = animalSpecieItem?.animal_weight
        binding.descTail.text = animalSpecieItem?.animal_tail
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