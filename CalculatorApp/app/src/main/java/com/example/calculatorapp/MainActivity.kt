package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helpClickListner = HelpClickListner()

        // Numbers
        // 1
        binding.oneButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.oneButton,
                binding.text
            )
        }

        // 2
        binding.twoButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.twoButton,
                binding.text
            )
        }

        // 3
        binding.threeButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.threeButton,
                binding.text
            )
        }

        // 4
        binding.fourButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.fourButton,
                binding.text
            )
        }

        // 5
        binding.fiveButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.fiveButton,
                binding.text
            )
        }

        // 6
        binding.sixButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.sixButton,
                binding.text
            )
        }

        // 7
        binding.oneButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.oneButton,
                binding.text
            )
        }

        // 8
        binding.twoButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.twoButton,
                binding.text
            )
        }

        // 9
        binding.oneButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.oneButton,
                binding.text
            )
        }

        // 0
        binding.twoButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.twoButton,
                binding.text
            )
        }

        // .
        binding.oneButton.setOnClickListener {
            helpClickListner.onClickNumber(
                binding.oneButton,
                binding.text
            )
        }

    }
}