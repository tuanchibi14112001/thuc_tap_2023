package com.example.animalapp.ui.quizz_game.end_quizz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.QuetionEndItemBinding
import com.example.animalapp.model.QuizzItem

class EndQuizzAdapter (private val itemClick: EndQuizzItemListener) : ListAdapter<QuizzItem, EndQuizzViewHolder>(AnswerItemDiffUtilCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EndQuizzViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuetionEndItemBinding.inflate(inflater,parent,false)
        return EndQuizzViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: EndQuizzViewHolder, position: Int) {
        return holder.bindItem(getItem(position))

    }

    class AnswerItemDiffUtilCallBack : DiffUtil.ItemCallback<QuizzItem>(){
        override fun areItemsTheSame(oldItem: QuizzItem, newItem: QuizzItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: QuizzItem, newItem: QuizzItem): Boolean {
            return oldItem == newItem
        }

    }




}