package com.example.animalapp.ui.memory_game

import com.example.animalapp.model.MemoryCardItem

interface CardItemClickListener {
    fun itemOnClick(memoryCardItem: MemoryCardItem)
}