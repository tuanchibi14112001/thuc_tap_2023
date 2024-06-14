package com.example.animalapp.ui.image_predict

import android.net.Uri
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
import com.example.animalapp.databinding.FragmentResultInfoBinding
import com.example.animalapp.model.AnimalFamilyItem
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.AnimalFamily
import com.example.animalapp.utils.MySharedPreferences
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class ResultInfoFragment : BaseFragment<FragmentResultInfoBinding>(), OtherResultItemClickListener {
    private val viewModel: ResultInfoViewModel by viewModels()
    private var resultInfo: AnimalFamily? = null
    private lateinit var animalFamilyAdapter: AnimalFamilyAdapter
    private var file: File? = null

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentResultInfoBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        var imgUri: Uri? = null
        val mySharedPreferences = MySharedPreferences(requireContext())
        val token = mySharedPreferences.getUserToken()
        imgUri = Uri.parse(args?.getString("img_uri"))
        val animalPredictResult: AnimalPredictResult? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                args?.getSerializable("predict_res", AnimalPredictResult::class.java)
            } else {
                args?.getSerializable("predict_res") as AnimalPredictResult
            }
        Log.d("CHECK", animalPredictResult.toString())
        imgUri?.let {
            binding.userImg.setImageURI(it)
            file = File(it.path!!)
        }

        animalPredictResult?.let {
            val animefName = it.result
            val results: MutableList<String> = it.similar.toMutableList()
            val resultTxt = "I think this is: " + animefName
            binding.resultTxt.text = resultTxt
            results.add(0, it.result)
            viewModel.getOtherResults(results)
            observeOtherResultData()
            setRv()
        }
        binding.btnAddGallery.setOnClickListener {
            file?.let { imageFile ->
                val requestBody: RequestBody = imageFile
                    .asRequestBody("image/*".toMediaTypeOrNull())
                val part =
                    MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
                viewModel.postImageToGallery(token,1,part)
                observeUploadImageResultData()
            }
        }
    }

    private fun updateMoreInfoUi(info: AnimalFamilyItem) {
        binding.imgAnimal.visibility = View.VISIBLE
        binding.scrDesc.visibility = View.VISIBLE
        binding.imgAnimal.load(
            info.img_url
        )
        binding.txtDes.text = info.desc
    }

    private fun setRv() {
        animalFamilyAdapter = AnimalFamilyAdapter(this)
        binding.recvOtherResults.apply {
            adapter = animalFamilyAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)
        }
    }


    private fun observeOtherResultData() {
        viewModel.otherResultDataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    resultInfo = it.data
                    resultInfo?.let { infoList ->
                        val predictAnimalInfo = infoList[0]
                        if (predictAnimalInfo.is_exist == 1)
                            updateMoreInfoUi(predictAnimalInfo)
                        val otherResult = infoList.drop(1)
                        animalFamilyAdapter.submitList(otherResult)
                    }
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    it.message?.let { it1 -> Log.d("CHECK_ERR", it1) }
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }
    private fun observeUploadImageResultData() {
        viewModel.uploadImageDataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), it.data?.result, Toast.LENGTH_SHORT).show()
                    hideLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    it.message?.let { it1 -> Log.d("CHECK_ERR", it1) }
                    hideLoading()
                }

                Status.LOADING -> {
                    showLoading()
                }
            }
        }
    }

    override fun itemOnClick(animalFamilyItem: AnimalFamilyItem) {
        if (animalFamilyItem.is_exist == 1) {
            val bundle = Bundle().apply {
                putSerializable("animal_family_item", animalFamilyItem)
            }
            findNavController().navigate(
                R.id.action_resultInfoFragment_to_familyDetailFragment,
                bundle
            )
        } else {
            Toast.makeText(requireContext(), "No information in the database", Toast.LENGTH_SHORT)
                .show()
        }
    }


}