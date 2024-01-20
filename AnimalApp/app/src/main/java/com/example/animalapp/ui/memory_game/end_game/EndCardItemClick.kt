package com.example.animalapp.ui.memory_game.end_game

import com.example.animalapp.model.MemoryCardItem

interface EndCardItemClick {
    fun itemOnClick(memoryCardItem: MemoryCardItem)
}