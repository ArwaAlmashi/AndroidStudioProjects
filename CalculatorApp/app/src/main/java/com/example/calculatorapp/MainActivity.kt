package com.example.calculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Error
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val integerChars = '0'..'9'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Numbers
        binding.oneButton.setOnClickListener { appendOnClick("1") }
        binding.twoButton.setOnClickListener { appendOnClick("2") }
        binding.threeButton.setOnClickListener { appendOnClick("3") }
        binding.fourButton.setOnClickListener { appendOnClick("4") }
        binding.fiveButton.setOnClickListener { appendOnClick("5") }
        binding.sixButton.setOnClickListener { appendOnClick("6") }
        binding.sevenButton.setOnClickListener { appendOnClick("7") }
        binding.eightButton.setOnClickListener { appendOnClick("8") }
        binding.nineButton.setOnClickListener { appendOnClick("9") }
        binding.zeroButton.setOnClickListener { appendOnClick("0") }
        binding.dotButton.setOnClickListener { appendOnClick(".") }

        // Operators
        binding.plusButton.setOnClickListener { appendOnClick(" + ") }
        binding.minusButton.setOnClickListener { appendOnClick(" - ") }
        binding.multiplyButton.setOnClickListener { appendOnClick(" * ") }
        binding.divisionButton.setOnClickListener { appendOnClick(" / ") }
        binding.negativeButton.setOnClickListener { appendOnClick(" +- ") }

        // Clear
        binding.cButton.setOnClickListener { clear() }

        //Del
        binding.delButton.setOnClickListener { del() }

        // Equal
        binding.equalButton.setOnClickListener { answer() }
    }

    // Save Balance value
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("expression", binding.expressionText.text.toString())
        outState.putString("answer", binding.answerText.text.toString())
    }

    // Restore Balance value
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.expressionText.text = savedInstanceState.getString("expression")
        binding.answerText.text = savedInstanceState.getString("answer")
    }

    @SuppressLint("SetTextI18n")
    private fun appendOnClick(userInput: String) {
        //if (binding.expressionText.text != "0")
        if (binding.answerText.text == "" ) {
            binding.expressionText.text = "${binding.expressionText.text}$userInput"
        } else if (isNumber(userInput)){
            clear()
            binding.expressionText.text = "${binding.expressionText.text}$userInput"
        } else {
            binding.expressionText.text = "${binding.answerText.text}$userInput"
            binding.answerText.text = ""
        }
    }

    private fun isNumber(input: String): Boolean {
        var dotOccurred = 0
        return input.all { it in integerChars || it == '.' && dotOccurred++ < 1 }
    }

    private fun clear() {
        binding.expressionText.text = ""
        binding.answerText.text = ""
    }

    private fun del() {
        binding.expressionText.text = binding.expressionText.text.dropLast(1)
    }

    private fun answer() {
        val text = binding.expressionText.text.toString()
        val exp = ExpressionBuilder(text).build()
        try {
            val result = exp.evaluate()
            val longResult = result.toLong()

            if (result == longResult.toDouble()) {
                binding.answerText.text = longResult.toString()
            } else {
                binding.answerText.text = result.toString()
            }
        } catch (e : Exception) {
            Toast.makeText(this, "${e.message}", Toast.LENGTH_LONG).show()
            clear()
        }


    }

}

