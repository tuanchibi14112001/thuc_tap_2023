package com.example.animalapp.ui.memory_game.end_game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentEndGameBinding
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.MemoryCardItem
import com.example.animalapp.ui.animal_family_detail.SpeciesAnimalAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EndGameFragment : BaseFragment<FragmentEndGameBinding>(), EndCardItemClick {
    private lateinit var endGameAdapter: EndGameAdapter
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEndGameBinding {
        return FragmentEndGameBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        val animalList = args?.getSerializable("animal_list") as MemoryCard
        val score = args?.getString("user_score")
        binding.txtScore.text = score
//        Log.d("CHECK", animalList.toString())
        endGameAdapter = EndGameAdapter(this)
        binding.recvGameItem.apply {
            adapter = endGameAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            setHasFixedSize(true)
        }
        endGameAdapter.submitList(animalList)
    }

    override fun itemOnClick(memoryCardItem: MemoryCardItem) {
        val bundle = Bundle().apply {
            putInt("animal_breed_detail", memoryCardItem.id)
        }
        findNavController().navigate(R.id.action_endGameFragment_to_animalSpeciesFragment, bundle)

    }


}