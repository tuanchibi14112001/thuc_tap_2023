package com.example.animalapp.ui.gallery.gallery_detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentGalleryDetailBinding
import com.example.animalapp.model.GalleryDetailItem
import com.example.animalapp.utils.MySharedPreferences
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryDetailFragment : BaseFragment<FragmentGalleryDetailBinding>(), GalleryDetailListener {
    private val viewModel: GalleryDetailViewModel by viewModels()
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
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        animal_specie_name?.let {
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
                        if (numsPhoto > 1) {
                            val txt = "$numsPhoto Photos"
                            binding.txtNumPhoto.text = txt
                        } else {
                            val txt = "$numsPhoto Photo"
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

//    private fun showDialogImage(galleryDetailItem: GalleryDetailItem) {
//        val dialog  = Dialog(requireContext())
//        dialog.setContentView(R.layout.custom_dialog_zoom)
//        val imageView = dialog.findViewById(R.id.imageViewDiaLog) as ImageView
//        imageView.load(galleryDetailItem.img_url){
//            error(R.drawable.ques_mark)
//        }
//        val buttonClose = dialog.findViewById<ImageView>(R.id.btnImageClose)
//        buttonClose.setOnClickListener{
//            dialog.dismiss()
//        }
//
//        dialog.show()
//
//    }

    override fun itemOnClick(galleryDetailItem: GalleryDetailItem) {
        val bundle = Bundle().apply {
            putString("img_url", galleryDetailItem.img_url)
            putInt("img_id", galleryDetailItem.id)
        }
        findNavController().navigate(R.id.action_galleryDetailFragment_to_imageFullFragment, bundle)
//        Toast.makeText(requireContext(), galleryDetailItem.img_url, Toast.LENGTH_SHORT).show()
    }


}