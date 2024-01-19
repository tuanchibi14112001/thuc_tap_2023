package com.example.animalapp.logic

import com.example.animalapp.model.MemoryCardItem
import com.example.animalapp.ui.memory_game.MemoryGameAdapter
import com.example.animalapp.ui.memory_game.MemoryGameFragment

class MemoryGameLogic(
    val memoryCardList: MutableList<MemoryCardItem>,
    val memoryGameView: MemoryGameFragment
) {
    private var mAdapter: MemoryGameAdapter? = null
    var cardsPositions: ArrayList<Int> = arrayListOf()
    private var nClicks = 0
    private var nPoints = 0
    private var firstMemoryCard: MemoryCardItem? = null
    private var secondMemoryCard: MemoryCardItem? = null

    fun startGame() {
        mAdapter = memoryGameView.memoryGameAdapter
    }

    fun handelClick(position: Int) {
        val memoryCard = memoryCardList[position]
        cardsPositions.add(position)
        nClicks++
        if (nClicks == 1) {
            handleFirstClick(memoryCard)
        } else if (nClicks == 2) {
            handleSecondClick(position, memoryCard)
        }

    }

    fun handleSecondClick(position: Int, memoryCard: MemoryCardItem) {

    }

    fun handleFirstClick(memoryCard: MemoryCardItem) {
        if (memoryCard.is_clicked == 1) {
            nClicks--;
            cardsPositions.remove(0)
        } else{
            firstMemoryCard = memoryCard
            memoryCard.is_clicked = 1
        }
    }

}