package com.example.animalapp.ui.image_predict

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.model.AnimalSpecieItem


class AnimalSpecieAdapter(private val itemClickListener: OtherResultItemClickListener) :
    ListAdapter<AnimalSpecieItem, AnimalSpecieViewHolder>(
        AnimalDiffUtilCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalSpecieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BreedItemBinding.inflate(inflater, parent, false)
        return AnimalSpecieViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: AnimalSpecieViewHolder, position: Int) {
        return holder.bindAnimalSpecie(getItem(position))
    }
    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<AnimalSpecieItem>() {
        override fun areItemsTheSame(
            oldItem: AnimalSpecieItem,
            newItem: AnimalSpecieItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AnimalSpecieItem,
            newItem: AnimalSpecieItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}