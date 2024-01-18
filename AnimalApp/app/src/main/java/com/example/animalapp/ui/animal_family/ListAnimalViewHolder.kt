package com.example.animalapp.ui.animal_family

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.databinding.FamilyItemBinding
import com.example.animalapp.model.AnimalFamilyItem

class ListAnimalViewHolder(
    private val itemBinding: FamilyItemBinding,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnimal(animalFamilyItem: AnimalFamilyItem) {
        itemBinding.imgAnimalItem.load(
            animalFamilyItem.img_url
        ) {
            crossfade(true)
            crossfade(500)
        }
        itemBinding.txtAnimalName.text = animalFamilyItem.name
        itemBinding.cvAnimalFamily.setOnClickListener {
            itemClickListener.itemOnClick(animalFamilyItem)
        }
    }
}