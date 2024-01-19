package com.example.animalapp.ui.memory_game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentMemoryGameBinding
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.MemoryCardItem
import com.example.animalapp.ui.animal_family_detail.SpeciesAnimalAdapter
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MemoryGameFragment : BaseFragment<FragmentMemoryGameBinding>(), CardItemClickListener {
    private val viewModel: MemoryGameViewModel by viewModels()
    lateinit var memoryGameAdapter: MemoryGameAdapter
    var cardList : MutableList<MemoryCardItem> = mutableListOf()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMemoryGameBinding {
        return FragmentMemoryGameBinding.inflate(inflater,container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.getMemoryCard()
        observeModel()
        setRv()

    }
    private fun setRv(){
        memoryGameAdapter = MemoryGameAdapter(this)
        binding.recvCardItem.apply {
            adapter = memoryGameAdapter
            layoutManager = GridLayoutManager(
                requireContext(), 2
            )
            setHasFixedSize(true)
        }
    }

    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    cardList = it.data!!
                    cardList.shuffle()
                    memoryGameAdapter.submitList(cardList)
                  Log.d("CHECK", cardList.toString())
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

    override fun itemOnClick(memoryCardItem: MemoryCardItem) {
    }



}