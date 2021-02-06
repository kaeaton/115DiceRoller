package com.laborofloathing.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private var numSides = 6
    private lateinit var diceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        val decreaseButton: Button = findViewById(R.id.decrease_button)
        rollButton.setOnClickListener {
            rollDice()
        }
        decreaseButton.setOnClickListener {
            decreaseDiceSize()
        }
        diceImage = findViewById<ImageView>(R.id.dice_image)
    }

    private fun decreaseDiceSize() {
        if (numSides > 1) numSides--
        Toast.makeText(this, numSides.toString(), Toast.LENGTH_SHORT).show()
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