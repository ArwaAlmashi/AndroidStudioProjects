package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

lateinit var recyclerView1 : RecyclerView
lateinit var editText1: EditText
lateinit var sendButton1: Button
var count1 : Int = 3

var textList : ArrayList<MyText> = ArrayList()
val textRecycleView : TextRecycleView by lazy { TextRecycleView() }

class MainActivityNumber : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.phrase_activity_main)

        recyclerView1 = findViewById(R.id.myRecycleView)
        editText1 = findViewById(R.id.userInput)
        sendButton1 = findViewById(R.id.sendButton)

        recyclerView1.adapter = textRecycleView
        var randomNumber: Int = Random.nextInt(11)


        sendButton1.setOnClickListener {

            var userNumber: Editable? = editText1.text
            textList.add(MyText("You guess ${userNumber}"))
            textRecycleView.setText(textList)
            editText1.setText("")

            if ("$randomNumber" == userNumber.toString()) {
                textList.add(MyText("Success"))
                textRecycleView.setText(textList)
                textList.add(MyText("Finish Game"))
                textRecycleView.setText(textList)
            } else {
                if (count1 == 1) {
                    textList.add(MyText("Finish Game, Correct number = $randomNumber"))
                    textRecycleView.setText(textList)
                } else {
                    count1--
                    textList.add(MyText("You have ${count1} guesses left"))
                    textRecycleView.setText(textList)
                }

            }


        }
    }
}