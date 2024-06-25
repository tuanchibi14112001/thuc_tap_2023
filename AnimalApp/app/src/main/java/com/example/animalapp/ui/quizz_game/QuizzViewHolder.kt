package com.example.animalapp.ui.quizz_game

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.animalapp.R
import com.example.animalapp.databinding.QuestionItemBinding
import com.example.animalapp.model.AnswerItem


class QuizzViewHolder(
    private val correctAns: String,
    private val itemBinding: QuestionItemBinding,
    private val itemClickListener: QuizzItemClick,
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindAnswer(answerItem: AnswerItem, isChecked: Int) {
        itemBinding.txtAnswer.text = answerItem.answer
        if(isChecked == 0) {
            itemBinding.cardAnswerItem.setOnClickListener {
                if (answerItem.answer == correctAns) {
                    correctAnsView()
                } else {
                    errorAnsView()
                }
                itemClickListener.itemOnClick(answerItem)
            }
        }
        else if(isChecked == 1){
            if (answerItem.answer == correctAns) {
                correctAnsView()
            } else {
                errorAnsView()
            }
            itemBinding.cardAnswerItem.setOnClickListener(null)
        }
        else{
            itemBinding.cardAnswerItem.setOnClickListener(null)
        }
    }

    fun bindAnswerViewGone(answerItem: AnswerItem){
        itemBinding.root.visibility = View.GONE
    }


    private fun correctAnsView() {
        itemBinding.cardAnswerItem.setBackgroundResource(R.drawable.green_background)
        itemBinding.txtAnswer.setTextColor(
            ContextCompat.getColor(
                itemBinding.root.context,
                R.color.white
            )
        )
        val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_tick)
        itemBinding.txtAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            drawable,
            null
        )
    }

    private fun errorAnsView(){
        itemBinding.cardAnswerItem.setBackgroundResource(R.drawable.red_background)
        itemBinding.txtAnswer.setTextColor(
            ContextCompat.getColor(
                itemBinding.root.context,
                R.color.white
            )
        )
        val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_cancel)
        itemBinding.txtAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            drawable,
            null
        )

    }

}