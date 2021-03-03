package com.laborofloathing.diceroller

import android.app.Application
import timber.log.Timber

class DiceRollerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}