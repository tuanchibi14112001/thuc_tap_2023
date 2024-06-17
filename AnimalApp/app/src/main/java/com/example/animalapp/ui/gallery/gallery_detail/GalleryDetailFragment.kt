package com.example.animalapp.ui.gallery.gallery_detail

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
import com.example.animalapp.databinding.FragmentGalleryDetailBinding
import com.example.animalapp.model.GalleryDetailItem
import com.example.animalapp.utils.MySharedPreferences
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryDetailFragment : BaseFragment<FragmentGalleryDetailBinding>(), GalleryDetailListener {
    private  val viewModel: GalleryDetailViewModel by viewModels()
    private lateinit var galleryDetailAdapter: GalleryDetailAdapter
    private var galleryDetail: MutableList<GalleryDetailItem> = mutableListOf()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGalleryDetailBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val animal_specie_name = args?.getString("specie_detail_name")
        val mySharedPreferences = MySharedPreferences(requireContext())
        val token = mySharedPreferences.getUserToken()
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
        animal_specie_name?.let{
            val txt = "${animal_specie_name} Photos"
            binding.textView.text = txt
            viewModel.getGalleryDetail(token, it)
            observeModel()
            setRv()
        }
    }

    private fun setRv() {
        galleryDetailAdapter = GalleryDetailAdapter(this)
        binding.recvList.apply {
            adapter = galleryDetailAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
            setHasFixedSize(true)
        }
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        galleryDetail = data
                        galleryDetailAdapter.submitList(galleryDetail)
                        val numsPhoto = galleryDetail.size
                        if(numsPhoto > 1){
                            val txt  = "$numsPhoto Photos"
                            binding.txtNumPhoto.text = txt
                        } else {
                            val txt  = "$numsPhoto Photo"
                            binding.txtNumPhoto.text = txt
                        }

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

    override fun itemOnClick(galleryDetailItem: GalleryDetailItem) {
        Toast.makeText(requireContext(), galleryDetailItem.img_url, Toast.LENGTH_SHORT).show()
    }

}