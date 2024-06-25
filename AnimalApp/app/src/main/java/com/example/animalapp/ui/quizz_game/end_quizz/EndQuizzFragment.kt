package com.example.animalapp.ui.quizz_game.end_quizz

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animalapp.R
import com.example.animalapp.base.BaseFragment
import com.example.animalapp.databinding.FragmentEndQuizBinding
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.QuizzItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EndQuizzFragment : BaseFragment<FragmentEndQuizBinding>(), EndQuizzItemListener {
    private lateinit var endQuizzAdapter: EndQuizzAdapter
    private var quizz: Quizz? = null
    private var txt_score : String = ""
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEndQuizBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        val args = this.arguments
        args?.let {
            quizz =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getSerializable("quizz", Quizz::class.java)
                } else {
                    it.getSerializable("quizz") as Quizz
                }
            txt_score = it.getString("quizz_score").toString()
            if (txt_score != "null"){
                binding.txtScore.text = txt_score
            }
        }
        quizz ?.let {
            setRcv()
            endQuizzAdapter.submitList(it)

        }
    }

    private fun setRcv(){
        endQuizzAdapter = EndQuizzAdapter(this)
        binding.recvQuizzItem.apply {
            adapter = endQuizzAdapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            setHasFixedSize(true)
        }
    }

    override fun itemOnClick(quizzItem: QuizzItem) {
        val bundle = Bundle().apply {
            putInt("animal_breed_detail", quizzItem.id)
        }
        findNavController().navigate(R.id.action_endQuizzFragment_to_animalBreedFragment, bundle)
    }

}