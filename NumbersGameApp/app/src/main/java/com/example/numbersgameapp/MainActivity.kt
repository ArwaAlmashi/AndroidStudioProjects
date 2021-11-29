package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

lateinit var recyclerView : RecyclerView
lateinit var editText: EditText
lateinit var sendButton: Button

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

        sendButton.setOnClickListener {
            textList.add(MyText("You guess ${editText.text.toString()}"))
            textRecycleView.setText(textList)
            editText.setText("")

            textList.add(MyText("You guess ${editText.text.toString()}"))
            textRecycleView.setText(textList)
            editText.setText("")
        }
    }
}