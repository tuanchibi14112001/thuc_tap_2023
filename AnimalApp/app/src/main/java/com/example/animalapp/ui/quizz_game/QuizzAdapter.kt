package com.example.animalapp.ui.quizz_game

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.animalapp.databinding.QuestionItemBinding
import com.example.animalapp.model.AnswerItem


class QuizzAdapter(private val itemClick: QuizzItemClick, private val correctAns: String) : ListAdapter<AnswerItem, QuizzViewHolder>(AnswerItemDiffUtilCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizzViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemBinding.inflate(inflater,parent,false)
        return QuizzViewHolder(correctAns,binding, itemClick)
    }

    override fun onBindViewHolder(holder: QuizzViewHolder, position: Int) {
        if(currentList.size == 4){
            return holder.bindAnswer(getItem(position), 0)
        }
        if(currentList.size == 5){
            if(position >= 4) {
                return holder.bindAnswerViewGone(getItem(position))
            }
            if(getItem(position).answer == currentList[4].answer || getItem(position).answer == correctAns){
                return holder.bindAnswer(getItem(position), 1)
            }
            else{
                Log.d("CHECK", "2")
                return holder.bindAnswer(getItem(position), 2)
            }
        }


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