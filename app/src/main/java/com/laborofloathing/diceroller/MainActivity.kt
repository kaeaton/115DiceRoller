package com.laborofloathing.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    private var numSides = 6
    private lateinit var diceImage: ImageView
    private lateinit var decreaseButton: Button
    private val NUM_SIDES_KEY = "NumOfSides"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate called")

        if (savedInstanceState != null) {
            numSides = savedInstanceState.getInt(NUM_SIDES_KEY)
        }

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }
        decreaseButton = findViewById(R.id.decrease_button)
        decreaseButton.setOnClickListener {
            numSides--
            decreaseDiceSize()
        }
        diceImage = findViewById<ImageView>(R.id.dice_image)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Timber.i("onSaveInstanceState called")

        outState.putInt(NUM_SIDES_KEY, numSides)
    }

    private fun decreaseDiceSize() {
        when (numSides) {
            1 -> decreaseButton.visibility = View.GONE
            else -> decreaseButton.text = getString(R.string.decreasing_button_text, (numSides - 1))
        }
    }

    private fun rollDice() {
        val randomInt = Random().nextInt(numSides) + 1
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }
}