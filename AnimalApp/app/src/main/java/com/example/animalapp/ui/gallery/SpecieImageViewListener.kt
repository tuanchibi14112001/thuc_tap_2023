package com.example.animalapp.ui.gallery

import com.example.animalapp.model.SpecieGalleryItem

interface SpecieImageViewListener {
    fun itemOnClick(specieGalleryItem: SpecieGalleryItem)
}