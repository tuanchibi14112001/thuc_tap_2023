package com.example.animalapp.ui.search

import android.content.res.ColorStateList
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.recyclerview.widget.RecyclerView
import com.example.animalapp.R
import com.example.animalapp.databinding.SearchItemBinding
import com.example.animalapp.model.SearchDetailItem

class SearchViewHolder(
    private val itemBinding: SearchItemBinding,
    private val itemClickListener: SearchItemListener

) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindItem(searchDetailItem: SearchDetailItem) {
        itemBinding.txtName.text = searchDetailItem.name
        if (searchDetailItem.type == 1) {
            itemBinding.txtType.text = "Specie"
        } else {
            itemBinding.txtType.text = "Breed"
        }
        itemBinding.cvSearchItem.setOnClickListener {
            itemClickListener.itemOnclick(searchDetailItem)
        }
    }

    fun bindItemSearched(searchDetailItem: SearchDetailItem, query: String) {
        val dataText = searchDetailItem.name
        val startPos = dataText.lowercase().indexOf(query.lowercase())
        val endPos = startPos + query.length
        if (startPos != -1) {
            val spannableString = SpannableString(dataText)
            spannableString.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        itemBinding.root.context,
                        R.color.red
                    )
                ), startPos, endPos, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            itemBinding.txtName.text = spannableString
            if (searchDetailItem.type == 1) {
                itemBinding.txtType.text = "Specie"
            } else {
                itemBinding.txtType.text = "Breed"
            }
            itemBinding.cvSearchItem.setOnClickListener {
                itemClickListener.itemOnclick(searchDetailItem)
            }
        } else {
            return bindItem(searchDetailItem)
        }
    }

}