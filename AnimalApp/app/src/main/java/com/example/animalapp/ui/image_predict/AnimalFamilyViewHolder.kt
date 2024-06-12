package com.example.animalapp.ui.image_predict


import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.R
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.model.AnimalFamilyItem


class AnimalFamilyViewHolder (
    private val itemBinding: BreedItemBinding,
    private val itemClickListener: OtherResultItemClickListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnimalFamily(animalFamilyItem: AnimalFamilyItem) {
        if(animalFamilyItem.is_exist == 1) {
            itemBinding.imgSpeciesItem.load(
                animalFamilyItem.img_url
            ) {
                crossfade(true)
                crossfade(500)
            }
        } else{
            itemBinding.imgSpeciesItem.load(
                R.drawable.ques_mark
            ) {
                crossfade(true)
                crossfade(500)
            }
        }
        itemBinding.txtSpeciesItemName.text = animalFamilyItem.name
        itemBinding.cvSpecies.setOnClickListener {
            itemClickListener.itemOnClick(animalFamilyItem)
        }
    }
}