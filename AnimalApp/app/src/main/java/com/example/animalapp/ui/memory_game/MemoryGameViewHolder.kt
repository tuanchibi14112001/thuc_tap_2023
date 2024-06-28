package com.example.animalapp.ui.memory_game

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
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

        if (memoryCardItem.is_clicked) {
            itemBinding.imgSpeciesItem.load(memoryCardItem.img_url)
        } else {
            itemBinding.imgSpeciesItem.load(R.drawable.ques_mark)
        }
        if(memoryCardItem.is_checked){
            itemBinding.imgSpeciesItem.alpha = 0.7f
        }
        itemBinding.cvGameItem.setOnClickListener {
            itemClickListener.itemOnClick(memoryCardItem)
        }
    }
}