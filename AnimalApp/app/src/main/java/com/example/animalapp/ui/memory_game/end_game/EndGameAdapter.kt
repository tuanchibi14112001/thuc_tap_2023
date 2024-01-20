package com.example.animalapp.ui.memory_game.end_game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.databinding.CardGameItemBinding
import com.example.animalapp.model.MemoryCardItem
import com.example.animalapp.ui.memory_game.CardItemClickListener
import com.example.animalapp.ui.memory_game.MemoryGameViewHolder

class EndGameAdapter (private val itemClickListener: EndCardItemClick) :
    ListAdapter<MemoryCardItem, EndGameViewHolder>(AnimalDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EndGameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BreedItemBinding.inflate(inflater, parent, false)
        return EndGameViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: EndGameViewHolder, position: Int) {
        return holder.bindAnimal(getItem(position))
    }

    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<MemoryCardItem>() {
        override fun areItemsTheSame(
            oldItem: MemoryCardItem,
            newItem: MemoryCardItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MemoryCardItem,
            newItem: MemoryCardItem
        ): Boolean {
            return oldItem == newItem
        }

    }

}