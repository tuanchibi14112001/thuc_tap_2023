package com.example.animalapp.ui.quizz_game

import androidx.recyclerview.widget.RecyclerView
import com.example.animalapp.databinding.CardGameItemBinding
import com.example.animalapp.databinding.QuestionItemBinding
import com.example.animalapp.model.AnswerItem
import com.example.animalapp.ui.memory_game.CardItemClickListener


class QuizzViewHolder(
    private val itemBinding: QuestionItemBinding,
    private val itemClickListener: QuizzItemClick
) : RecyclerView.ViewHolder(itemBinding.root){
    fun bindAnswer(answerItem: AnswerItem){
        itemBinding.txtAnswer.text = answerItem.answer
        itemBinding.cardAnswerItem.setOnClickListener{
            itemClickListener.itemOnClick(answerItem)
        }
    }

}