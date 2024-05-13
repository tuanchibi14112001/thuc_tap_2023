package com.example.animalapp.ui.quizz_game

import com.example.animalapp.model.AnswerItem
import com.example.animalapp.model.QuizzItem

interface QuizzItemClick {
    fun itemOnClick(answerItem: AnswerItem)
}