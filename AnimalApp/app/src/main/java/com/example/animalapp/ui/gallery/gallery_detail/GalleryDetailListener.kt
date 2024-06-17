package com.example.animalapp.ui.gallery.gallery_detail

import com.example.animalapp.model.GalleryDetailItem

interface GalleryDetailListener {
    fun itemOnClick(galleryDetailItem: GalleryDetailItem)
}