package com.example.todoapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var toDoList: ArrayList<ToDo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            addNewItemAlert()
        }
        binding.toDoRecyclerView.adapter = ToDoAdapter(toDoList)
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteButton -> {
                toDoList.removeAll(toDoList)
                val rv : RecyclerView = findViewById(R.id.toDoRecyclerView)
                rv.adapter = ToDoAdapter(toDoList)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Alert
    private fun addNewItemAlert() {
        val dialogBuilder = AlertDialog.Builder(this)
        val input = EditText(this)

        dialogBuilder.setMessage("Enter an item")
            .setPositiveButton("Add", DialogInterface.OnClickListener { dialog, id ->
                toDoList.add(ToDo(input.text.toString(), false))
            })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(input)
        alert.show()
    }
}

