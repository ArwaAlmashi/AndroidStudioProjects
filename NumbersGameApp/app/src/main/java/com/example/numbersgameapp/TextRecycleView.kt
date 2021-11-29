package com.example.numbersgameapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextRecycleView: RecyclerView.Adapter<TextRecycleView.TextViewHolder>() {


    var textList: ArrayList<MyText> = ArrayList()

    fun setText(textList: java.util.ArrayList<MyText>) {
        this.textList = textList
        notifyDataSetChanged()
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myText: TextView = itemView.findViewById(R.id.textInRecycleView)

        fun bind(text: MyText){
            myText.text = text.text
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        var txt : MyText = textList.get(position)
        holder.bind(txt)
    }

    override fun getItemCount(): Int {
        return textList.size
    }

}