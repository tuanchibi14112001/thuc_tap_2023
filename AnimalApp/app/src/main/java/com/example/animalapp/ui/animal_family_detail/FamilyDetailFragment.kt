package com.example.animalapp.ui.animal_family_detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentFamilyDetailBinding
import com.example.animalapp.model.AnimalFamilyItem
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FamilyDetailFragment : BaseFragment<FragmentFamilyDetailBinding>(), SpeciesItemClickListener {
    private val viewModel: FamilyDetailViewModel by viewModels()
    private lateinit var speciesAnimalAdapter: SpeciesAnimalAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFamilyDetailBinding {
        return FragmentFamilyDetailBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val familyItem: AnimalFamilyItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            args?.getSerializable("animal_family_item", AnimalFamilyItem::class.java)
        } else {
            args?.getSerializable("animal_family_item") as AnimalFamilyItem
        }
        familyItem?.let {
            binding.txtName.text = it.name
            binding.txtDes.text = it.desc
            binding.imgAnimal.load(
                it.img_url
            )
            viewModel.getAnimalBreed(it.id)
            observeModel()
            setRv()
        }

    }

    private fun setRv(){
        speciesAnimalAdapter = SpeciesAnimalAdapter(this)
        binding.recvBreedItem.apply {
            adapter = speciesAnimalAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)
        }
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    speciesAnimalAdapter.submitList(it.data)
//                    Log.d("CHECK", it.data.toString())
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    override fun itemOnClick(animalSpecieItem: AnimalSpecieItem) {
        val bundle = Bundle().apply {
            putSerializable("animal_species_detail", animalSpecieItem)
        }
        findNavController().navigate(R.id.action_familyDetailFragment_to_animalSpeciesFragment, bundle)
    }


}