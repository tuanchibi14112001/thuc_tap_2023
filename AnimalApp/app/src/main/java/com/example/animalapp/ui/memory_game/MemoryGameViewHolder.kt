package com.example.animalapp.ui.memory_game

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.R
import com.example.animalapp.databinding.CardGameItemBinding
import com.example.animalapp.model.MemoryCardItem

class MemoryGameViewHolder(
    private val itemBinding: CardGameItemBinding,
    private val itemClickListener: CardItemClickListener
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnimal(memoryCardItem: MemoryCardItem) {
        if (memoryCardItem.is_clicked == 1) {
            itemBinding.imgSpeciesItem.load(R.drawable.ques_mark)
        }
        if (memoryCardItem.is_checked == 0) {
            itemBinding.imgSpeciesItem.load(memoryCardItem.img_url){
                crossfade(true)
                crossfade(300)
            }
        }
        if(memoryCardItem.is_checked == 1){
            itemBinding.cvGameItem.visibility = View.GONE
        }
        itemBinding.cvGameItem.setOnClickListener{
            itemClickListener.itemOnClick(memoryCardItem)
        }
    }
}