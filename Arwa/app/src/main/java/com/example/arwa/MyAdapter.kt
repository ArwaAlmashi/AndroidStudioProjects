package com.example.arwa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arwa.databinding.ListItemBinding

class MyAdapter(private var textItemList: ArrayList<TextItem>) :
    RecyclerView.Adapter<MyAdapter.myHolder>() {

    // Holder
    class myHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    // Adapter functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        val item = textItemList[position]

        holder.binding.apply {
            itemText.text = item.text

            if (item.amount == 0.0) {
                //toDoTextView.setTextColor(Color.parseColor("#ACACAC"))
            }
        }
//        holder.binding.toDoCheckbox.setOnClickListener {
//            notifyDataSetChanged()
//        }
    }

    override fun getItemCount(): Int = textItemList.size
}