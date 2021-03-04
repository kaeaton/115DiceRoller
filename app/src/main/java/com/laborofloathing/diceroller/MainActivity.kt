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
    private var sideShowing = 1
    private lateinit var diceImage: ImageView
    private lateinit var decreaseButton: Button
    private val NUM_SIDES_KEY = "NumOfSides"
    private val SIDE_SHOWING = "SideShowing"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate called")

        diceImage = findViewById<ImageView>(R.id.dice_image)

        if (savedInstanceState != null) {
            numSides = savedInstanceState.getInt(NUM_SIDES_KEY)
            setDiceImage(savedInstanceState.getInt(SIDE_SHOWING))
            Timber.i("numSides read from the bundle")
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Timber.i("onSaveInstanceState called")

        outState.putInt(NUM_SIDES_KEY, numSides)
        outState.putInt(SIDE_SHOWING, sideShowing)
        Timber.i("numSides written to bundle")
    }

    private fun decreaseDiceSize() {
        when (numSides) {
            1 -> decreaseButton.visibility = View.GONE
            else -> decreaseButton.text = getString(R.string.decreasing_button_text, (numSides - 1))
        }
    }

    private fun rollDice() {
        sideShowing = Random().nextInt(numSides) + 1
        setDiceImage(sideShowing)
    }

    private fun setDiceImage(sideShowing: Int) {
        val drawableResource = when (sideShowing) {
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