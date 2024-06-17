package com.example.animalapp.ui.gallery.gallery_detail

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.R
import com.example.animalapp.databinding.GalleryDetailItemBinding
import com.example.animalapp.model.GalleryDetailItem


class GalleryDetailViewHolder(
    private val itemBinding: GalleryDetailItemBinding,
    private val itemClickListener: GalleryDetailListener

) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindItem(galleryDetailItem: GalleryDetailItem) {
        itemBinding.imageItem.load(galleryDetailItem.img_url) {
            error(R.drawable.ques_mark)
        }
        itemBinding.cvDetailItemGallery.setOnClickListener {
            itemClickListener.itemOnClick(galleryDetailItem)
        }
    }

}