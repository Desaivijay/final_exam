package com.vijay.die_rolling_app
import java.util.*

class Dice(private val sides: Int) {
    fun roll(): Int {
        return Random().nextInt(sides) + 1
    }
}