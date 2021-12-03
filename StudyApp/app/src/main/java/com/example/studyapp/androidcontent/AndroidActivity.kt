package com.example.studyapp.androidcontent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.kotlincontent.KotlinActivity
import com.example.studyapp.R

lateinit var androidRecyclerView: RecyclerView
lateinit var androidContentList: ArrayList<AndroidContent>
val androidRecyclerViewAdapter: AndroidRecyclerViewAdapter by lazy { AndroidRecyclerViewAdapter() }

class AndroidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        title = "Android Content"

        androidRecyclerView = findViewById(R.id.android_recyclerview)
        androidRecyclerView.adapter = androidRecyclerViewAdapter

        androidContentList = arrayListOf(
            AndroidContent(
                "AndroidManifest.xml ",
                "Describe your app in this file; your app starts with the “main” method you declare here. You also need to declare all of your activities here"
            ),
            AndroidContent(
                "Activity",
                "Is a Java controller class that typically corresponds to one screen in your app"
            ),
            AndroidContent(
                "Service",
                " background services, like notifications"
            ),
            AndroidContent(
                "View",
                "widgets like TextView, ImageView, Button ..."
            )
        )
        androidRecyclerViewAdapter.setContent(androidContentList)
    }

    // Menus
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item -> {
                val intent = Intent(this, KotlinActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item: MenuItem = menu!!.getItem(0)
        item.title = "Kotlin Content"
        return super.onPrepareOptionsMenu(menu)
    }



}