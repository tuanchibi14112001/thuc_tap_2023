package com.example.animalapp.ui.quizz_game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.QuestionItemBinding
import com.example.animalapp.model.AnswerItem


class QuizzAdapter(private val itemClick: QuizzItemClick) : ListAdapter<AnswerItem, QuizzViewHolder>(AnswerItemDiffUtilCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizzViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemBinding.inflate(inflater,parent,false)
        return QuizzViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: QuizzViewHolder, position: Int) {
        return holder.bindAnswer(getItem(position))
    }

    class AnswerItemDiffUtilCallBack : DiffUtil.ItemCallback<AnswerItem>(){
        override fun areItemsTheSame(oldItem: AnswerItem, newItem: AnswerItem): Boolean {
            return oldItem.answer == newItem.answer
        }
        override fun areContentsTheSame(oldItem: AnswerItem, newItem: AnswerItem): Boolean {
            return oldItem == newItem
        }

    }




}