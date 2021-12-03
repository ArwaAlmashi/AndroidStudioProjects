package com.example.guessthephraseupdated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var phraseNumber = 0
    var guessesLetters = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phrases = listOf<String>("I love android", "Hello World")
        var phrase = decodePhrase(phrases[phraseNumber])

        val currentPhrase = findViewById<TextView>(R.id.text_phrase)
        currentPhrase.text = "Phrase: $phrase\nGuesses letters: ${letters(guessesLetters)}"

    }

    private fun decodePhrase(phrase: String): String {
        var decodedPhrase =phrase
        for (i in decodedPhrase.indices) {
            if (decodedPhrase[i] != ' ')
                decodedPhrase = decodedPhrase.replace(decodedPhrase[i], '*', true)
        }
        return decodedPhrase
    }

    private fun letters(array: ArrayList<String>): String {
        var allLetters = ""
        for (i in array)
            allLetters = "$allLetters $i"
        return allLetters
    }
}