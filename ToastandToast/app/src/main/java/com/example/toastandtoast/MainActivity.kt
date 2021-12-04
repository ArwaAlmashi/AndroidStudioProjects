package com.example.toastandtoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sButton = findViewById<Button>(R.id.snackbar_button)
        val tButton = findViewById<Button>(R.id.toast_button)

        sButton.setOnClickListener {
            val mainLayout = findViewById<ConstraintLayout>(R.id.main_layout)
            Snackbar.make(mainLayout, "Snack", Snackbar.LENGTH_SHORT).show()
        }

        tButton.setOnClickListener {
            Toast.makeText(this, "Toast", Toast.LENGTH_LONG).show()
        }

    }
}