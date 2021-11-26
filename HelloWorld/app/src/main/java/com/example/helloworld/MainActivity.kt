package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myMain  =  findViewById<ConstraintLayout>(R.id.mainID)
        Snackbar.make(myMain,"onCreate",Snackbar.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        val myMain  =  findViewById<ConstraintLayout>(R.id.mainID)
        Snackbar.make(myMain,"onResume",Snackbar.LENGTH_LONG).show()
    }
    override fun onRestart() {
        super.onRestart()
        val myMain  =  findViewById<ConstraintLayout>(R.id.mainID)
        Snackbar.make(myMain,"onRestart",Snackbar.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        val myMain  =  findViewById<ConstraintLayout>(R.id.mainID)
        Snackbar.make(myMain,"onStart",Snackbar.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        val myMain  =  findViewById<ConstraintLayout>(R.id.mainID)
        Snackbar.make(myMain,"onStop",Snackbar.LENGTH_LONG).show()
    }

}