package com.example.numbersgameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

lateinit var recyclerView : RecyclerView
lateinit var editText: EditText
lateinit var sendButton: Button
var count : Int = 9

var textPhraseList : ArrayList<MyTextPhrase> = ArrayList()
val PHRASE_TEXT_RECYCLE_VIEW : PhraseTextRecycleView by lazy { PhraseTextRecycleView() }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.phrase_activity_main)

        recyclerView1 = findViewById(R.id.myRecycleView)
        editText1 = findViewById(R.id.userInput)
        sendButton1 = findViewById(R.id.sendButton)

        recyclerView1.adapter = PHRASE_TEXT_RECYCLE_VIEW

        val phrase = "I love the android"
        var phraseEncoding : String = phrase
        for (i in phrase.indices){
            if (phrase[i] != ' ')
            phraseEncoding= phraseEncoding.replace(phrase[i], '*', true)
        }
        textPhraseList.add(MyTextPhrase(phraseEncoding))
        PHRASE_TEXT_RECYCLE_VIEW.setText(textPhraseList)

        textPhraseList.add(MyTextPhrase("Guess the phrase"))
        PHRASE_TEXT_RECYCLE_VIEW.setText(textPhraseList)

        sendButton1.setOnClickListener {
            val intent = Intent(this, MainActivityNumber::class.java)
            startActivity(intent)

//            val userInput: String = editText1.text.toString()
//            var founded: Int = 0
//            if (userInput.length == 1 ) {
//                for (i in phrase.indices){
//                    if (phrase[i] == userInput[0] ) {
//                        val chars = phraseEncoding.toCharArray()
//                        chars[i] = userInput[0]
//                        phraseEncoding = String(chars)
//                        founded++
//                        Log.d("MainActivity", "(${phraseEncoding})")
//                    }
//                }
//                textPhraseList[0].text = phraseEncoding
//                textPhraseList.add(MyTextPhrase("Found $founded ${userInput.uppercase()}(s)"))
//                textPhraseList.add(MyTextPhrase("${count1--} guesses remaining"))
//                PHRASE_TEXT_RECYCLE_VIEW.setText(textPhraseList)
//                editText1.setText("")
//            } else if (phrase != userInput) {
//                textPhraseList.add(MyTextPhrase("(${userInput}) is wrong guess"))
//                PHRASE_TEXT_RECYCLE_VIEW.setText(textPhraseList)
//                editText1.setText("")
//                textPhraseList.add(MyTextPhrase("Guess a letter"))
//                PHRASE_TEXT_RECYCLE_VIEW.setText(textPhraseList)
//            } else {
//                textPhraseList.add(MyTextPhrase("Correct"))
//                PHRASE_TEXT_RECYCLE_VIEW.setText(textPhraseList)
//                editText1.setText("")
//            }


        }
    }
}

