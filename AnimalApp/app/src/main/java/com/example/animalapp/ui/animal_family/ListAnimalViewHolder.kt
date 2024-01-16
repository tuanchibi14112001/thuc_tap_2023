package com.example.animalapp.ui.animal_family

import android.view.View
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
            crossfade(1000)
        }
        itemBinding.txtAnimalName.text = animalFamilyItem.name
        itemBinding.cvAnimalFamily.setOnClickListener {
            itemClickListener.animalFamilyOnClick(animalFamilyItem)
        }
    }
}