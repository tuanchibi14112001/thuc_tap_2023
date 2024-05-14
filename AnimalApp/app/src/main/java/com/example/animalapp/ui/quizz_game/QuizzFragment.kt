package com.example.animalapp.ui.quizz_game

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
import com.example.animalapp.databinding.FragmentQuizzBinding
import com.example.animalapp.model.AnswerItem
import com.example.animalapp.model.Quizz
import com.example.animalapp.model.QuizzItem
import com.example.animalapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuizzFragment : BaseFragment<FragmentQuizzBinding>(), QuizzItemClick {
    private val viewModel: QuizzViewModel by viewModels()
    lateinit var quizzAdapter: QuizzAdapter
    private var quizzList: MutableList<QuizzItem> = mutableListOf()
    private var quizz: Quizz? = null
    private var scores: Int? = 0
    private var position: Int = 0

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentQuizzBinding.inflate(inflater, container, false)

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.getQuizz()
        observeModel()
    }


    private fun observeModel() {
        viewModel.dataFlow.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    quizz = it.data
                    if (quizz != null) {
                        quizzList = quizz as Quizz
                    }
                    setQuizzView()
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

    private fun loadAnswers() {
        var answers: MutableList<AnswerItem> = mutableListOf()

        quizzList[position].answers.forEach{
            var answerItem = AnswerItem("")
            answerItem.answer = it
            answers.add(answerItem)
            Log.d("CHECK", answers.toString())
        }
        quizzAdapter = QuizzAdapter(this)
        quizzAdapter.submitList(answers)
        binding.recvAnswerList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = quizzAdapter
        }

    }

    private fun setQuizzView() {
        binding.apply {
            btnBack.setOnClickListener{
                findNavController().navigate(R.id.action_quizzFragment_to_homeFragment)
            }
            progressBar.progress = 1
            setQuizzItemView()
            btnNextQuizz.setOnClickListener{
                if(progressBar.progress == 10){
                   finishQuizz()
                    return@setOnClickListener
                }
                position++
                progressBar.progress +=1
                var text =  "Question "+ progressBar.progress + "/10"
                txtQuestionNum.text = text
                setQuizzItemView()

            }

        }
    }

    private fun setQuizzItemView(){
        binding.txtQuestion.text = quizzList[position].correctAnswer
        binding.imgQuizz.load(quizzList[position].img_url)
        loadAnswers()
    }

    private fun  finishQuizz(){
        findNavController().navigate(R.id.action_quizzFragment_to_homeFragment)
    }

    override fun itemOnClick(answerItem: AnswerItem) {
        if(answerItem.answer == quizzList[position].correctAnswer){
            correctViewUpdate()
        }
    }
    private fun correctViewUpdate(){

    }

}