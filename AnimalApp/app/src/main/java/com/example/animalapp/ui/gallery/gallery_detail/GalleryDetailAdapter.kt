package com.example.animalapp.ui.gallery.gallery_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.GalleryDetailItemBinding
import com.example.animalapp.model.GalleryDetailItem


class GalleryDetailAdapter(private val itemClickListener: GalleryDetailListener) :
    ListAdapter<GalleryDetailItem, GalleryDetailViewHolder>(
        AnimalDiffUtilCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GalleryDetailItemBinding.inflate(inflater, parent, false)
        return GalleryDetailViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: GalleryDetailViewHolder, position: Int) {
        return holder.bindItem(getItem(position))
    }

    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<GalleryDetailItem>() {
        override fun areItemsTheSame(
            oldItem: GalleryDetailItem,
            newItem: GalleryDetailItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GalleryDetailItem,
            newItem: GalleryDetailItem
        ): Boolean {
            return oldItem == newItem
        }

    }

}