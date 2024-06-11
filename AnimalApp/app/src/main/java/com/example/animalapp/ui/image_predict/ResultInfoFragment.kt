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
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultInfoFragment : BaseFragment<FragmentResultInfoBinding>() {
    private val viewModel: ResultInfoViewModel by viewModels()
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
            val resultTxt = "I think this is: " + animefName
            binding.resultTxt.text = resultTxt
            viewModel.getMoreInfo(animefName)
            observeModel()
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

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("CHECK", it.data.toString())
                    val moreInfo: MoreInfo? = it.data
                    moreInfo?.let { info ->
                        if (info.is_exist == 1) {
                            updateMoreInfoUi(info)
                        }
                    }
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


}