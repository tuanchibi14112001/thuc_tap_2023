package com.example.animalapp.ui.animal_family_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.model.AnimalBreedItem

class SpeciesAnimalAdapter(private val itemClickListener: SpeciesItemClickListener) :
    ListAdapter<AnimalBreedItem, SpeciesAnimalViewHolder>(AnimalDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesAnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BreedItemBinding.inflate(inflater, parent, false)
        return SpeciesAnimalViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: SpeciesAnimalViewHolder, position: Int) {
        return holder.bindAnimal(getItem(position))
    }

    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<AnimalBreedItem>() {
        override fun areItemsTheSame(
            oldItem: AnimalBreedItem,
            newItem: AnimalBreedItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AnimalBreedItem,
            newItem: AnimalBreedItem
        ): Boolean {
            return oldItem == newItem
        }

    }

}