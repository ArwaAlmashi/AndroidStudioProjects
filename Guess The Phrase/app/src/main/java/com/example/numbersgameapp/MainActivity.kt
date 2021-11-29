package com.example.numbersgameapp

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

var textList : ArrayList<MyText> = ArrayList()
val textRecycleView : TextRecycleView by lazy { TextRecycleView() }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.myRecycleView)
        editText = findViewById(R.id.userInput)
        sendButton = findViewById(R.id.sendButton)

        recyclerView.adapter = textRecycleView

        val phrase = "I love the android"
        var phraseEncoding : String = phrase
        for (i in phrase.indices){
            if (phrase[i] != ' ')
            phraseEncoding= phraseEncoding.replace(phrase[i], '*', true)
        }
        textList.add(MyText(phraseEncoding))
        textRecycleView.setText(textList)

        textList.add(MyText("Guess the phrase"))
        textRecycleView.setText(textList)

        sendButton.setOnClickListener {

            val userInput: String = editText.text.toString()
            var founded: Int = 0
            if (userInput.length == 1 ) {
                for (i in phrase.indices){
                    if (phrase[i] == userInput[0] ) {
                        val chars = phraseEncoding.toCharArray()
                        chars[i] = userInput[0]
                        phraseEncoding = String(chars)
                        founded++
                        Log.d("MainActivity", "(${phraseEncoding})")
                    }
                }
                textList[0].text = phraseEncoding
                textList.add(MyText("Found $founded ${userInput.uppercase()}(s)"))
                textList.add(MyText("${count--} guesses remaining"))
                textRecycleView.setText(textList)
                editText.setText("")
            } else if (phrase != userInput) {
                textList.add(MyText("(${userInput}) is wrong guess"))
                textRecycleView.setText(textList)
                editText.setText("")
                textList.add(MyText("Guess a letter"))
                textRecycleView.setText(textList)
            } else {
                textList.add(MyText("Correct"))
                textRecycleView.setText(textList)
                editText.setText("")
            }


        }
    }
}

