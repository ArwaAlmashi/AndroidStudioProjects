package com.example.guessthephraseupdated

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


lateinit var recyclerView : RecyclerView
lateinit var editText: EditText
lateinit var sendButton: Button
lateinit var currentPhrase : TextView
private lateinit var sharedPreferences: SharedPreferences

var statementList : ArrayList<Statement> = ArrayList()
val recyclerViewAdapter : RecyclerViewAdapter by lazy { RecyclerViewAdapter() }

val phrases = listOf("I love android", "Hello World")
var guessesLetters: ArrayList<String> = ArrayList()

var switcher : Int = 0
var phraseNumber :Int = 0
var chance : Int = 9

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        editText = findViewById(R.id.user_input)
        sendButton = findViewById(R.id.send_button)
        currentPhrase = findViewById(R.id.text_phrase)

        recyclerView.adapter = recyclerViewAdapter

        var phrase = phrases[phraseNumber]
        var decodedPhaser = decodePhrase(phrase)
        currentPhrase.text = "Phrase: $decodedPhaser\nGuesses letters: ${letters(guessesLetters)}"


        sendButton.setOnClickListener {
            if (switcher % 2 == 0) {
                if (editText.text.toString() == phrases[phraseNumber]) {
                    statementList.add(Statement("Correct", Color.GREEN))
                    alert()
                    if (phraseNumber <= phrases.size) {
                        phraseNumber++
                        phrase = phrases[phraseNumber]
                        decodedPhaser = decodePhrase(phrase)
                        currentPhrase.text =
                            "Phrase: $decodedPhaser\nGuesses letters: ${letters(guessesLetters)}"
                    }
                } else {
                    statementList.add(Statement("Incorrect",Color.RED))
                }
                editText.setText("")
                editText.hint = "Guess a letter"
                switcher++
            } else {
                val userInput = editText.text.toString()
                var foundedLetters = 0
                for (i in phrase.indices) {
                    if (phrase[i] == userInput[0]) {
                        val chars = decodedPhaser.toCharArray()
                        chars[i] = userInput[0]
                        decodedPhaser = String(chars)
                        foundedLetters++
                    }
                }
                currentPhrase.text =
                    "Phrase: $decodedPhaser\nGuesses letters: ${letters(guessesLetters)}"
                statementList.add(Statement("Found $foundedLetters ${userInput[0].uppercase()}(s)", Color.GREEN))
                statementList.add(Statement("${chance--} guesses remaining",Color.BLACK))
                editText.setText("")

                editText.hint = "Guess the phrase"
                switcher++
            }
            recyclerViewAdapter.setStatement(statementList)

        }
        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var myMessage =
            sharedPreferences.getString("myMessage", "")
                .toString()
        with(sharedPreferences.edit()) {
            putString("myMessage", myMessage)
            apply()
        }

    }

    private fun decodePhrase(phrase: String): String {
        var decodedPhrase = phrase
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

    private fun alert() {

        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Game Over")
        //set message for alert dialog
        builder.setMessage("Do you want play again?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            statementList.removeAll(statementList)
            recyclerViewAdapter.setStatement(statementList)
            chance = 0
            switcher = 0
        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
            editText.isEnabled = false
        }
        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            editText.isEnabled = false
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }
}