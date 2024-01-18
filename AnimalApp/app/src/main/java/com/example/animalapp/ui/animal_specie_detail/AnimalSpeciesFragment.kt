package com.example.animalapp.ui.animal_specie_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentAnimalSpeciesBinding
import com.example.animalapp.model.AnimalSpecieItem


class AnimalSpeciesFragment : BaseFragment<FragmentAnimalSpeciesBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnimalSpeciesBinding {
        return FragmentAnimalSpeciesBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val animalSpecieItem = args?.getSerializable("animal_species_detail") as AnimalSpecieItem
//        Log.d("CHECK", animalSpecieItem.toString())
        bindView(animalSpecieItem)

    }

    private fun bindView(animalSpecieItem: AnimalSpecieItem) {
        binding.txtName.text = animalSpecieItem.name
        binding.imgAnimal.load(animalSpecieItem.img_url)
        binding.descName.text = animalSpecieItem.name
        binding.descLength.text = animalSpecieItem.animal_length
        binding.descComment.text = animalSpecieItem.comments
        binding.descLife.text = animalSpecieItem.average_lifespan
        binding.descWeight.text = animalSpecieItem.animal_weight
        binding.descTail.text = animalSpecieItem.animal_tail
    }
}