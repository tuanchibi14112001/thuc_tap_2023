package com.example.animalapp.ui.quizz_game.end_quizz

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animalapp.R
import com.example.animalapp.databinding.QuetionEndItemBinding
import com.example.animalapp.model.QuizzItem

class EndQuizzViewHolder(
    private val itemBinding: QuetionEndItemBinding,
    private val itemClickListener: EndQuizzItemListener,
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bindItem(quizzItem: QuizzItem) {
        itemBinding.txtName.text = quizzItem.correctAnswer
        itemBinding.imgAnimal.load(quizzItem.img_url) {
            error(R.drawable.ques_mark)
        }
        if (quizzItem.clickedAnswer == quizzItem.correctAnswer) {
            correctAnsView()
        } else {
            errorAnsView()
        }
        itemBinding.cvItem.setOnClickListener{
            itemClickListener.itemOnClick(quizzItem)
        }
    }

    private fun correctAnsView() {
        itemBinding.cvItem.setBackgroundResource(R.drawable.green_background)
        itemBinding.txtName.setTextColor(
            ContextCompat.getColor(
                itemBinding.root.context,
                R.color.white
            )
        )
        val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_tick)
        itemBinding.txtName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            drawable,
            null
        )
    }

    private fun errorAnsView() {
        itemBinding.cvItem.setBackgroundResource(R.drawable.red_background)
        itemBinding.txtName.setTextColor(
            ContextCompat.getColor(
                itemBinding.root.context,
                R.color.white
            )
        )
        val drawable = ContextCompat.getDrawable(itemBinding.root.context, R.drawable.ic_cancel)
        itemBinding.txtName.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            drawable,
            null
        )

    }

}