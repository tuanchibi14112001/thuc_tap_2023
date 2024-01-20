package com.example.animalapp.ui.memory_game.end_game

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.model.MemoryCardItem

class EndGameViewHolder (
    private val itemBinding: BreedItemBinding,
    private val itemClickListener: EndCardItemClick
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnimal(memoryCardItem: MemoryCardItem) {
        itemBinding.imgSpeciesItem.load(
            memoryCardItem.img_url
        ) {
            crossfade(true)
            crossfade(500)
        }
        itemBinding.txtSpeciesItemName.text = memoryCardItem.name
        itemBinding.cvSpecies.setOnClickListener {
            itemClickListener.itemOnClick(memoryCardItem)
        }
    }
}