package com.example.animalapp.ui.gallery.gallery_detail

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentImageFullBinding
import com.example.animalapp.utils.MySharedPreferences
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageFullFragment : BaseFragment<FragmentImageFullBinding>() {
    private val viewModel: ImageFullViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentImageFullBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {

        val mySharedPreferences = MySharedPreferences(requireContext())
        val token = mySharedPreferences.getUserToken()
        val builder = AlertDialog.Builder(requireContext())

        arguments?.let {
            val img_url = it.getString("img_url")
            val img_id = it.getInt("img_id")
            binding.imgZoom.load(img_url) {
                error(R.drawable.ques_mark)
            }
            binding.btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            binding.btnImageDelete.setOnClickListener {
                if (token != "null")
                    showDialog(builder, token, img_id)
                else
                    Toast.makeText(requireContext(), "You need to login", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        Toast.makeText(requireContext(), data.result, Toast.LENGTH_SHORT).show()
                        if (data.status) {
                            findNavController().popBackStack()
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

    private fun showDialog(builder: AlertDialog.Builder, token: String, img_id: Int) {
        val show = builder.setTitle("Alert!")?.setMessage("Do you want to delete this photo?")
            ?.setPositiveButton("YES") { dialogInterface: DialogInterface, i: Int ->
                viewModel.deleteImageGallery(token, img_id)
                observeModel()
            }
            ?.setNegativeButton("CANCEL") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }

            ?.show()
    }
}