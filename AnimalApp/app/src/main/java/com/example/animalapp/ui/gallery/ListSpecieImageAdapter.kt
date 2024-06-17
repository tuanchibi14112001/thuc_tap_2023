package com.example.animalapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.SpecieImageItemBinding
import com.example.animalapp.model.SpecieGalleryItem


class ListSpecieImageAdapter(private val itemClickListener: SpecieImageViewListener) : ListAdapter<SpecieGalleryItem, ListSpecieImageViewHolder>(
    AnimalDiffUtilCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSpecieImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SpecieImageItemBinding.inflate(inflater, parent, false)
        return  ListSpecieImageViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ListSpecieImageViewHolder, position: Int) {
        return holder.bindItem(getItem(position))
    }

    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<SpecieGalleryItem>(){
        override fun areItemsTheSame(
            oldItem: SpecieGalleryItem,
            newItem: SpecieGalleryItem
        ): Boolean {
            return oldItem.animal_specie_name == newItem.animal_specie_name
        }

        override fun areContentsTheSame(
            oldItem: SpecieGalleryItem,
            newItem: SpecieGalleryItem
        ): Boolean {
            return oldItem == newItem
        }

    }

}