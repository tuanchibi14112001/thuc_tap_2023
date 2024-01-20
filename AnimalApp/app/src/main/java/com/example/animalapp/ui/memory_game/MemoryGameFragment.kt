package com.example.animalapp.ui.memory_game


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentMemoryGameBinding
import com.example.animalapp.model.MemoryCard
import com.example.animalapp.model.MemoryCardItem
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MemoryGameFragment : BaseFragment<FragmentMemoryGameBinding>(), CardItemClickListener {
    private val viewModel: MemoryGameViewModel by viewModels()
    lateinit var memoryGameAdapter: MemoryGameAdapter
    private lateinit  var builder: AlertDialog.Builder
    var cardList: MutableList<MemoryCardItem> = mutableListOf()
    private var animalList: MemoryCard ?= null
    private var indexOfSingleSelectedCard: Int? = null
    private var score: Int = 0

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMemoryGameBinding {
        return FragmentMemoryGameBinding.inflate(inflater, container, false)
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        builder = AlertDialog.Builder(requireContext())
        viewModel.getMemoryCard()
        observeModel()
        binding.txtMove.text = score.toString()
        setRv()

    }

    private fun setRv() {
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
                    animalList = it.data
                    cardList = it.data!!
                    cardList.shuffle()
                    memoryGameAdapter.submitList(cardList)
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
        val position = cardList.indexOf(memoryCardItem)
        updateModel(position, binding.txtMove, builder)
        updateView()
    }

    private fun updateView() {
        binding.recvCardItem.apply {
            adapter = memoryGameAdapter
        }
    }

    private fun updateModel(position: Int, txtMove: TextView, builder: Builder) {
        val card = cardList[position]
        if (card.is_clicked) {
            Toast.makeText(requireContext(), "Invalid move", Toast.LENGTH_SHORT).show()
            return
        }
        if (indexOfSingleSelectedCard == null) {
            restoreCard()
            indexOfSingleSelectedCard = position

        } else {
            checkForMatch(indexOfSingleSelectedCard!!, position, txtMove, builder)
            indexOfSingleSelectedCard = null
        }
        card.is_clicked = !card.is_clicked
        cardList[position] = card
        memoryGameAdapter.submitList(cardList)
    }


    private fun checkForMatch(
        position1: Int,
        position2: Int,
        txtMove: TextView,
        builder: Builder
    ) {
        if (cardList[position1].animal_family_id == cardList[position2].animal_family_id) {
            cardList[position1].is_checked = true
            cardList[position2].is_checked = true
            Toast.makeText(requireContext(), "Match found!", Toast.LENGTH_SHORT).show()
            plushPoint(txtMove)
        } else {
            minusPont(txtMove)
        }

        if (isEndGame()) {
            showDialog(builder)
        }
    }

    private fun minusPont(txtMove: TextView) {
        score = if (score >= 1) score - 1
        else
            0
        txtMove.text = score.toString()
    }

    private fun plushPoint(txtMove: TextView) {
        score += 10
        txtMove.text = score.toString()

    }

    private fun showDialog(builder: Builder) {
        val show = builder.setTitle("Alert!")?.setMessage("Your Scores: $score")
            ?.setPositiveButton("Replay") { dialogInterface: DialogInterface, i: Int ->
                findNavController().navigate(R.id.action_memoryGameFragment_self)
            }
            ?.setNegativeButton("End Game") { dialogInterface: DialogInterface, i: Int ->
                val bundle = Bundle().apply {
                    putString("user_score", score.toString())
                    putSerializable("animal_list", animalList)
                }
                findNavController().navigate(R.id.action_memoryGameFragment_to_endGameFragment, bundle)
            }

            ?.show()
    }

    private fun restoreCard() {
        cardList.forEach() { card ->
            if (!card.is_checked) {
                card.is_clicked = false
            }
        }
    }

    private fun isEndGame(): Boolean {
        for (card in cardList) {
            if (!card.is_checked)
                return false
        }
        return true

    }


}