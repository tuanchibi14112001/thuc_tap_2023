package com.example.daggerhilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.daggerhilt.databinding.TvShowItemBinding
import com.example.daggerhilt.models.TVShowItem

class TvShowAdapter :
    ListAdapter<TVShowItem, TvShowAdapter.TvShowViewHolder>(TvShowItemDiffUtilCallBack()) {
    inner class TvShowViewHolder(val binding: TvShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTvShowItem(tvShowItem: TVShowItem) {
            binding.txtTvItemName.text = tvShowItem.name
            binding.imgTvItem.load(tvShowItem.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = TvShowItemBinding.inflate(
            inflate, parent, false
        )
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        return holder.bindTvShowItem(getItem(position))
    }

    class TvShowItemDiffUtilCallBack : DiffUtil.ItemCallback<TVShowItem>() {
        override fun areItemsTheSame(oldItem: TVShowItem, newItem: TVShowItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVShowItem, newItem: TVShowItem): Boolean {
            return oldItem == newItem
        }

    }
}