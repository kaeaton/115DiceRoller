package com.laborofloathing.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.*

class MainActivity : AppCompatActivity() {

    private var numSides = 6
    private lateinit var diceImage: ImageView
    private lateinit var decreaseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }
        decreaseButton = findViewById(R.id.decrease_button)
        decreaseButton.setOnClickListener {
            decreaseDiceSize()
        }
        diceImage = findViewById<ImageView>(R.id.dice_image)
    }

    private fun decreaseDiceSize() {
        var text: String
        if (numSides > 1) {
            numSides--
            if (numSides >= 2) {
                text = getString(R.string.decreasing_button_text) + " " + (numSides - 1).toString()
                decreaseButton.setText(text)
            } else {
                decreaseButton.visibility = View.GONE
            }
            Toast.makeText(this, numSides.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun rollDice() {
        val randomInt = Random().nextInt(numSides) + 1
        val drawableResource = when(randomInt) {
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