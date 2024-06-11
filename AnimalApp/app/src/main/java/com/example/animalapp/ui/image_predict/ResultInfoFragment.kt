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
import coil.load
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentResultInfoBinding
import com.example.animalapp.model.AnimalFamilyItem
import com.example.animalapp.model.AnimalPredictResult
import com.example.animalapp.model.MoreInfo
import com.example.animalapp.model.MoreInfoList
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultInfoFragment : BaseFragment<FragmentResultInfoBinding>() {
    private val viewModel: ResultInfoViewModel by viewModels()
    private var resultInfo: MoreInfoList? = null
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentResultInfoBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        var imgUri: Uri? = null
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
        }

        animalPredictResult?.let {
            val animefName = it.result
            val results: MutableList<String> = it.similar.toMutableList()
            val resultTxt = "I think this is: " + animefName
            binding.resultTxt.text = resultTxt
            results.add(0, it.result)
            viewModel.getOtherResults(results)
            observeOtherResultData()
        }
    }

    private fun updateMoreInfoUi(info: MoreInfo) {
        binding.imgAnimal.visibility = View.VISIBLE
        binding.scrDesc.visibility = View.VISIBLE
        binding.imgAnimal.load(
            info.img_url
        )
        binding.txtDes.text = info.desc
    }

    private fun observeOtherResultData() {
        viewModel.otherResultDataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    resultInfo = it.data
                    resultInfo?.let { infoList ->
                        val predictAnimalInfo = infoList[0]
                        updateMoreInfoUi(predictAnimalInfo)
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


}