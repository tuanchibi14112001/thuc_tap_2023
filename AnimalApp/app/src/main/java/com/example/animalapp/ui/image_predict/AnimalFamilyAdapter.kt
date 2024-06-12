package com.example.animalapp.ui.image_predict

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.BreedItemBinding
import com.example.animalapp.model.AnimalFamilyItem


class AnimalFamilyAdapter(private val itemClickListener: OtherResultItemClickListener) :
    ListAdapter<AnimalFamilyItem, AnimalFamilyViewHolder>(
        AnimalDiffUtilCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalFamilyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BreedItemBinding.inflate(inflater, parent, false)
        return AnimalFamilyViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: AnimalFamilyViewHolder, position: Int) {
        return holder.bindAnimalFamily(getItem(position))
    }
    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<AnimalFamilyItem>() {
        override fun areItemsTheSame(
            oldItem: AnimalFamilyItem,
            newItem: AnimalFamilyItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AnimalFamilyItem,
            newItem: AnimalFamilyItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}