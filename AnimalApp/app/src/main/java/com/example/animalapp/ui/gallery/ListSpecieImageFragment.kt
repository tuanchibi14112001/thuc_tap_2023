package com.example.animalapp.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentListSpecieImageBinding
import com.example.animalapp.model.SpecieGalleryItem
import com.example.animalapp.utils.MySharedPreferences
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSpecieImageFragment : BaseFragment<FragmentListSpecieImageBinding>(),
    SpecieImageViewListener {
    private val viewModel: ListSpecieImageViewModel by viewModels()
    private lateinit var listSpecieImageAdapter: ListSpecieImageAdapter
    private var specieGallery: MutableList<SpecieGalleryItem> = mutableListOf()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListSpecieImageBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        val mySharedPreferences = MySharedPreferences(requireContext())
        val token = mySharedPreferences.getUserToken()
        viewModel.getUserGallery(token)
        observeModel()
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
        setRv()
    }

    private fun setRv() {
        listSpecieImageAdapter = ListSpecieImageAdapter(this)
        binding.recvSpecieList.apply {
            adapter = listSpecieImageAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { listGallery ->
                        specieGallery = listGallery
                        listSpecieImageAdapter.submitList(specieGallery)
                    }
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

    override fun itemOnClick(specieGalleryItem: SpecieGalleryItem) {
        val bundle = Bundle().apply {
            putString("specie_detail_name", specieGalleryItem.animal_specie_name)
        }
        findNavController().navigate(R.id.action_listSpecieImageFragment_to_galleryDetailFragment, bundle)
    }

}