package com.example.animalapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.SearchItemBinding
import com.example.animalapp.model.SearchDetailItem

class SearchViewAdapter(private val itemClickListener: SearchItemListener) :
    ListAdapter<SearchDetailItem, SearchViewHolder>(
        AnimalDiffUtilCallBack()
    ) {

    var queryText: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        if (queryText != null && queryText.isNotEmpty()){
            return holder.bindItemSearched(getItem(position), queryText)
        }
            return holder.bindItem(getItem(position))
    }

    class AnimalDiffUtilCallBack : DiffUtil.ItemCallback<SearchDetailItem>() {
        override fun areItemsTheSame(
            oldItem: SearchDetailItem,
            newItem: SearchDetailItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchDetailItem,
            newItem: SearchDetailItem
        ): Boolean {
            return oldItem == newItem
        }

    }

}