package com.vijay.die_rolling_app
import kotlin.random.Random

class Dice(private val sides: Int) {
    fun roll(): Int {
        return Random.nextInt(1, sides + 1)
    }
}