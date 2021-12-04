package com.example.todoapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ToDoItemBinding

class ToDoAdapter(private var toDoList: ArrayList<ToDo>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoHolder>() {

    // Holder
    class ToDoHolder(val binding: ToDoItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    // Adapter
    fun setToDoListItems(toDoListItems: java.util.ArrayList<ToDo>) {
        toDoList = toDoListItems
        notifyDataSetChanged()
    }

    // Override
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        return ToDoHolder(
            ToDoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false) //onClick
        )
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        val toDo = toDoList[position]

        holder.binding.apply {
            toDoTextView.text = toDo.text
            toDoCheckbox.isChecked = toDo.checked
            if (toDo.checked) {
                toDoTextView.setTextColor(Color.parseColor("#ACACAC"))
            } else {
                toDoTextView.setTextColor(Color.BLACK)
            }
        }
        holder.binding.toDoCheckbox.setOnClickListener {
            toDo.checked = toDo.checked.not()
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = toDoList.size


}