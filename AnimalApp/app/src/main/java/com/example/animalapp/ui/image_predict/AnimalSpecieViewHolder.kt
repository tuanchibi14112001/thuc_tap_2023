package com.example.animalapp.ui.image_predict


import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.R
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.model.AnimalSpecieItem


class AnimalSpecieViewHolder (
    private val itemBinding: BreedItemBinding,
    private val itemClickListener: OtherResultItemClickListener
) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnimalSpecie(animalSpecieItem: AnimalSpecieItem) {
        if(animalSpecieItem.is_exist == 1) {
            itemBinding.imgSpeciesItem.load(
                animalSpecieItem.img_url
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
        itemBinding.txtSpeciesItemName.text = animalSpecieItem.name
        itemBinding.cvSpecies.setOnClickListener {
            itemClickListener.itemOnClick(animalSpecieItem)
        }
    }
}