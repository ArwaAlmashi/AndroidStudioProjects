package com.example.studyapp.kotlincontent

import android.app.AlertDialog
import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.R
import com.example.studyapp.androidcontent.AndroidActivity


lateinit var recyclerView: RecyclerView
lateinit var kotlinContentList: ArrayList<KotlinContent>
val kotlinContentAdapter: KotlinRecyclerViewAdapter by lazy { KotlinRecyclerViewAdapter() }

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        title = "Kotlin content"

        recyclerView = findViewById(R.id.kotlin_recyclerview)
        recyclerView.adapter = kotlinContentAdapter

        kotlinContentList = arrayListOf(
            KotlinContent("Data Type", "Kotlin provides a set of built-in types"),
            KotlinContent(
                "Try/Catch",
                "It used to handle errors thrown by user entering wrong data type"
            ),
            KotlinContent(
                "Dictionaries",
                "Dictionaries in Kotlin are called Maps. A Map allows us to create key-value pairs"
            ),
            KotlinContent(
                "OOP",
                "Classes are used in kotlin to create objects that share some attributes and functionalities"
            ),
            KotlinContent("Arrays", "Are mutable. We can change each item at any time"),
            KotlinContent(
                "Lists",
                "Are immutable. Once we create a List, we cannot make any changes to it"
            ),
            KotlinContent("ArrayList", " It used to create a dynamic array")
        )

        kotlinContentAdapter.setContent(kotlinContentList)

        // OnClickListener
        kotlinContentAdapter.setOnKotlinItemClick(object :
            KotlinRecyclerViewAdapter.OnKotlinItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("MainActivity", "$position")
                alert(kotlinContentList[position].title, kotlinContentList[position].description)
            }

        })

    }

    // Menus
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item -> {
                val intent = Intent(this, AndroidActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Alert Message

    private fun alert(title: String, description: String) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(description)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing cancel action
        builder.setNeutralButton("Ok") { dialogInterface, which ->
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }


}