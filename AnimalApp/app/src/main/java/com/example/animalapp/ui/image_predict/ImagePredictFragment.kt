package com.example.animalapp.ui.image_predict

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentImagePredictBinding
import com.example.animalapp.utils.Status
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


@AndroidEntryPoint
class ImagePredictFragment : BaseFragment<FragmentImagePredictBinding>() {
    private val viewModel: ImagePredictViewModel by viewModels()
    private var imgUri: Uri ?= null
    private var file: File ?= null
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                imgUri = fileUri
                binding.imgPic.setImageURI(fileUri)
                file = File(fileUri.path!!)


            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentImagePredictBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.btnTakePic.setOnClickListener {
            ImagePicker.with(this)
                .crop()//Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
        binding.btnPredict.setOnClickListener{
            if(file == null){
                Toast.makeText(requireContext(), "Add your image", Toast.LENGTH_SHORT).show()
            }
            else{
                file?.let {
                    val requestBody: RequestBody = it
                        .asRequestBody("image/*".toMediaTypeOrNull())
                    val part = MultipartBody.Part.createFormData("file", it.name,requestBody)
                    viewModel.getAnimalNamePre(part)
                }
            }
        }
        observeModel()
    }


    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("CHECK", it.data.toString())
                    val result = "Result: " + it.data!!.result
                    binding.txtResult.text =   result
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    it.message?.let { it1 -> Log.d("CHECK", it1) }
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    override fun onDestroy() {
        imgUri = null
        file = null
        super.onDestroy()
    }

}