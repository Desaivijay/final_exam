package com.vijay.die_rolling_app
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vijay.die_rolling_app.databinding.ActivityMainBinding
import java.util.*




class MainActivity : AppCompatActivity() {
    var result = ""

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // Initialize view binding

   binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    // Initialize shared preferences
    sharedPreferences = getSharedPreferences("MyDicePrefs", Context.MODE_PRIVATE)

    // Set up dice spinner
    val diceOptions = arrayOf("4", "6", "8", "10", "12", "20")
    val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, diceOptions)
    binding.spinnerDice.adapter = adapter

    // Set up roll button click listener
        binding.buttonRoll.setOnClickListener {
            var selectedDice = binding.spinnerDice.selectedItem.toString().toInt()
            var result = rollDice(selectedDice)
            binding.textViewResult.text = getString(R.string.result, result.toString())
        }

    // Set up roll twice button click listener
    binding.checkBoxRollTwice.setOnClickListener {
        val selectedDice = binding.spinnerDice.selectedItem.toString()
        val result1 = rollDice(selectedDice.toInt())
        val result2 = rollDice(selectedDice.toInt())
        binding.textViewResult.text = getString(R.string.result_twice, result1.toString(), result2.toString())
    }


}

private fun rollDice(maxValue: Int): Int {
    val random = Random()
    return random.nextInt(maxValue) + 1
}

private fun onSaveCustomDice(customDiceValue: String) {
    if (customDiceValue.isNotEmpty()) {
        sharedPreferences.edit().putString("customDiceValue", customDiceValue).apply()
        Toast.makeText(this, "Custom dice saved!", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(this, "Please enter a valid value", Toast.LENGTH_SHORT).show()
    }
}

private fun onLoadCustomDice() {
    val customDiceValue = sharedPreferences.getString("customDiceValue", "")
    binding.editTextCustomSides.setText(customDiceValue)
}
}