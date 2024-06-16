package com.example.animalapp.ui.animal_family

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.databinding.FamilyItemBinding
import com.example.animalapp.model.AnimalSpecieItem

class ListAnimalViewHolder(
    private val itemBinding: FamilyItemBinding,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnimal(animalSpecieItem: AnimalSpecieItem) {
        itemBinding.imgAnimalItem.load(
            animalSpecieItem.img_url
        ) {
            crossfade(true)
            crossfade(500)
        }
        itemBinding.txtAnimalName.text = animalSpecieItem.name
        itemBinding.cvAnimalFamily.setOnClickListener {
            itemClickListener.itemOnClick(animalSpecieItem)
        }
    }
}