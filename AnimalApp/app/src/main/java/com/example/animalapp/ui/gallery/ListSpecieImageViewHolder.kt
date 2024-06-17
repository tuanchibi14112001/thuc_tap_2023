package com.example.animalapp.ui.gallery

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.R
import com.example.animalapp.databinding.SpecieImageItemBinding
import com.example.animalapp.model.SpecieGalleryItem

class ListSpecieImageViewHolder (
    private val itemBinding: SpecieImageItemBinding,
    private val itemClickListener: SpecieImageViewListener

): RecyclerView.ViewHolder(itemBinding.root){
    fun bindItem(specieGalleryItem: SpecieGalleryItem){
        itemBinding.specieImageItem.load(specieGalleryItem.img_url){
            error(R.drawable.ques_mark)
        }
        val txt = "${specieGalleryItem.animal_specie_name} - ${specieGalleryItem.total}"
        itemBinding.txtSpecie.text = txt
        itemBinding.cvSpecieItemGallery.setOnClickListener{
            itemClickListener.itemOnClick(specieGalleryItem)
        }
    }

}