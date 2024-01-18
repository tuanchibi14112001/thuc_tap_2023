package com.example.animalapp.ui.animal_family_detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentFamilyDetailBinding
import com.example.animalapp.model.AnimalFamilyItem


class FamilyDetailFragment : BaseFragment<FragmentFamilyDetailBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFamilyDetailBinding {
        return FragmentFamilyDetailBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val item : AnimalFamilyItem ?= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            args?.getSerializable("animal_family_item", AnimalFamilyItem::class.java)
        } else {
            args?.getSerializable("animal_family_item") as AnimalFamilyItem
        }
        item?.let {
            binding.txtName.text = it.name
            binding.txtDes.text= it.desc
            binding.imgAnimal.load(
                it.img_url
            )
        }

    }


}