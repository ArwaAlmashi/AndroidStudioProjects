package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

lateinit var recyclerView : RecyclerView
lateinit var editText: EditText
lateinit var sendButton: Button
var count : Int = 3

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
        var randomNumber: Int = Random.nextInt(11)

        textList.add(MyText("Random =  ${randomNumber}"))
        textRecycleView.setText(textList)

        sendButton.setOnClickListener {

            var userNumber: Editable? = editText.text
            textList.add(MyText("You guess ${userNumber}"))
            textRecycleView.setText(textList)
            editText.setText("")

            if ("$randomNumber" == userNumber.toString()) {
                textList.add(MyText("Success"))
                textRecycleView.setText(textList)
                textList.add(MyText("Finish Game"))
                textRecycleView.setText(textList)
            } else {
                if (count == 1) {
                    textList.add(MyText("Finish Game, Correct number = $randomNumber"))
                    textRecycleView.setText(textList)
                } else {
                    count--
                    textList.add(MyText("You have ${count} guesses left"))
                    textRecycleView.setText(textList)
                }

            }


        }
    }
}