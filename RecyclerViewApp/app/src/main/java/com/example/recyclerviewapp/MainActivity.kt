package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var editText: EditText
    lateinit var sendButton: Button

    var messageArrayList : ArrayList<Message> = ArrayList()
    val messageRecycleView : MessageRecycleView by lazy { MessageRecycleView() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.myRecycleView)
        editText = findViewById(R.id.messagePlainText)
        sendButton = findViewById(R.id.sendButton)

        recyclerView.adapter = messageRecycleView

        sendButton.setOnClickListener {
            messageArrayList.add(Message(editText.text.toString()))
            messageRecycleView.setMessage(messageArrayList)
            editText.setText("")
        }

    }
}