package com.example.tp3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val slider: Slider = findViewById(R.id.slider)
        val sliderValueText: TextView = findViewById(R.id.sliderValueText)

        slider.addOnChangeListener { _, value, _ ->
            val target = value.toInt()
            sliderValueText.text = "Nombre cible : $target"
            rollDice(target)
        }
    }

    private fun rollDice(target: Int) {
        val dice1 = Dice(6)
        val dice2 = Dice(6)

        val diceRoll1 = dice1.roll()
        val diceRoll2 = dice2.roll()
        val sum = diceRoll1 + diceRoll2

        val diceImage1: ImageView = findViewById(R.id.diceImage1)
        val diceImage2: ImageView = findViewById(R.id.diceImage2)
        val titre: TextView = findViewById(R.id.titre)
        val confettiGif: ImageView = findViewById(R.id.confettiGif)

        diceImage1.setImageResource(getDiceImage(diceRoll1))
        diceImage2.setImageResource(getDiceImage(diceRoll2))

        if (sum == target) {
            titre.text = "Gagné !"
            showConfetti(confettiGif)
        } else {
            titre.text = "Dommage !"
            hideConfetti(confettiGif)
        }
    }

    private fun getDiceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1
        }
    }

    private fun showConfetti(confettiGif: ImageView) {
        confettiGif.visibility = View.VISIBLE
        confettiGif.postDelayed({
            confettiGif.visibility = View.GONE
        }, 3000)
    }

    private fun hideConfetti(confettiGif: ImageView) {
        confettiGif.visibility = View.GONE
    }
}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}