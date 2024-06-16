package com.example.animalapp.ui.animal_family

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.FamilyItemBinding
import com.example.animalapp.model.AnimalSpecieItem


class ListAnimalAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<AnimalSpecieItem, ListAnimalViewHolder>(AnimalDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FamilyItemBinding.inflate(inflater, parent, false)
        return ListAnimalViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ListAnimalViewHolder, position: Int) {
        return holder.bindAnimal(getItem(position))
    }
    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<AnimalSpecieItem>(){
        override fun areItemsTheSame(oldItem: AnimalSpecieItem, newItem: AnimalSpecieItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: AnimalSpecieItem, newItem: AnimalSpecieItem): Boolean {
            return oldItem == newItem
        }

    }

}