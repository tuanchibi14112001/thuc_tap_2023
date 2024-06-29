package com.example.animalapp.ui.animal_specie_detail

import android.app.Dialog
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentSpecieDetailBinding
import com.example.animalapp.databinding.FragmentVideoBinding
import com.example.animalapp.model.AnimalSpecieItem
import com.example.animalapp.model.AnimalBreedItem
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SpecieDetailFragment : BaseFragment<FragmentSpecieDetailBinding>(), BreedItemClickListener {
    private val viewModel: SpecieDetailViewModel by viewModels()
    private lateinit var breedsAnimalAdapter: BreedsAnimalAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSpecieDetailBinding {
        return FragmentSpecieDetailBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val familyItem: AnimalSpecieItem? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                args?.getSerializable("animal_specie_item", AnimalSpecieItem::class.java)
            } else {
                args?.getSerializable("animal_specie_item") as AnimalSpecieItem
            }
        familyItem?.let {
            binding.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            binding.txtName.text = it.name
            binding.txtDes.text = it.desc
            binding.imgAnimal.load(
                it.img_url
            )
            if (it.video_url != null) {
                binding.btnShowVideo.visibility = View.VISIBLE
                val videoUrl = it.video_url
                binding.btnShowVideo.setOnClickListener{
                    val bundle = Bundle().apply {
                        putString("video_url", videoUrl)
                    }
                    findNavController().navigate(R.id.action_specieDetailFragment_to_videoFragment, bundle)
                }
            }
            viewModel.getAnimalBreed(it.id)
            observeModel()
            setRv()
            Log.d("CHECK", it.toString())
        }

    }

    private fun setRv() {
        breedsAnimalAdapter = BreedsAnimalAdapter(this)
        binding.recvBreedItem.apply {
            adapter = breedsAnimalAdapter
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
                    breedsAnimalAdapter.submitList(it.data)
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

    override fun itemOnClick(animalSpecieItem: AnimalBreedItem) {
        val bundle = Bundle().apply {
            putInt("animal_breed_detail", animalSpecieItem.id)
        }
        findNavController().navigate(
            R.id.action_specieDetailFragment_to_animalBreedFragment,
            bundle
        )
    }


}