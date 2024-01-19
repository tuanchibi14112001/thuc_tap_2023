package com.example.animalapp.ui.memory_game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.CardGameItemBinding
import com.example.animalapp.model.MemoryCardItem

class MemoryGameAdapter (private val itemClickListener: CardItemClickListener) :
    ListAdapter<MemoryCardItem, MemoryGameViewHolder>(AnimalDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryGameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardGameItemBinding.inflate(inflater, parent, false)
        return MemoryGameViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: MemoryGameViewHolder, position: Int) {
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