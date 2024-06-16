package com.example.animalapp.ui.animal_specie_detail

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.model.AnimalBreedItem

class BreedsAnimalViewHolder (
    private val itemBinding: BreedItemBinding,
    private val itemClickListener: BreedItemClickListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnimal(animalSpecieItem: AnimalBreedItem) {
        itemBinding.imgSpeciesItem.load(
            animalSpecieItem.img_url
        ) {
            crossfade(true)
            crossfade(500)
        }
        itemBinding.txtSpeciesItemName.text = animalSpecieItem.name
        itemBinding.cvSpecies.setOnClickListener {
            itemClickListener.itemOnClick(animalSpecieItem)
        }
    }
}