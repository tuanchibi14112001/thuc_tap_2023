package com.example.memorygame

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.memorygame.databinding.ActivityPlayGameBinding
import kotlinx.coroutines.delay


class PlayGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayGameBinding
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private lateinit var builder: AlertDialog.Builder

    private val iconCode: Int = R.drawable.baseline_question
    private var scores: Int = 0
    private var indexOfSingleSelectedCard: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayGameBinding.inflate(layoutInflater)
        builder = AlertDialog.Builder(this)
        scores = 0

        setContentView(binding.root)

        val images = mutableListOf(
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7,
            R.drawable.img_8,
            R.drawable.img_9,
            R.drawable.img_10
        )

        images.addAll(images)

        images.shuffle()

        buttons = listOf(
            binding.btnImg1, binding.btnImg2, binding.btnImg3, binding.btnImg4,
            binding.btnImg5, binding.btnImg6, binding.btnImg7, binding.btnImg8,
            binding.btnImg9, binding.btnImg10, binding.btnImg11, binding.btnImg12,
            binding.btnImg13, binding.btnImg14, binding.btnImg15, binding.btnImg16,
            binding.btnImg17, binding.btnImg18, binding.btnImg19, binding.btnImg20
        )

        cards = buttons.indices.map { index -> MemoryCard(images[index]) }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                updateModel(index, binding.scores, builder)
                updateView()
            }
        }
    }

    private fun updateView() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            button.setImageResource(if (card.isFaceup) card.identifier else iconCode)
        }

    }

    private fun updateModel(index: Int, text: TextView, builder: Builder) {
        val card = cards[index]
        if (card.isFaceup) {
            Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show()
            return
        }

        if (indexOfSingleSelectedCard == null) {
            restoreCard()
            indexOfSingleSelectedCard = index

        } else {
            checkForMatch(indexOfSingleSelectedCard!!, index, text, builder)
            indexOfSingleSelectedCard = null
        }
        card.isFaceup = !card.isFaceup

    }

    private fun restoreCard() {
        cards.forEach { card ->
            if (!card.isMatched) {
                card.isFaceup = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int, text: TextView, builder: Builder) {
        if (cards[position1].identifier == cards[position2].identifier) {
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            Toast.makeText(this, "Match found!", Toast.LENGTH_SHORT).show()
            plusPoints(text)
            Handler(Looper.getMainLooper()).postDelayed({
                buttons[position1].isInvisible = true
                buttons[position2].isInvisible = true
            }, 500)
        } else {
            minusPoints(text)
        }
        if (isEndGame()) {
            showDialog(builder)
        }
    }

    private fun plusPoints(text: TextView) {
        scores += 10
        text.setText(scores.toString())
    }

    private fun minusPoints(text: TextView) {
        scores = if (scores >= 1) scores - 1
        else
            0
        text.setText(scores.toString())
    }

    private fun isEndGame(): Boolean {
        for (card in cards) {
            if (!card.isMatched)
                return false
        }
        return true
    }

    private fun showDialog(builder: Builder) {
        val show = builder.setTitle("Alert!").setMessage("Your Scores: " + scores.toString())
            .setPositiveButton("Replay", { dialogInterface: DialogInterface, i: Int ->
                finish()
                val intent = Intent(this, PlayGameActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton("Go Home", { dialogInterface: DialogInterface, i: Int ->
                finish()
            })

            .show()

    }
}